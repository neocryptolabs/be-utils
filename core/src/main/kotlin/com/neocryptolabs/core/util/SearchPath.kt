package com.neocryptolabs.core.util

import com.neocryptolabs.core.model.*
import com.neocryptolabs.core.util.*
import java.math.BigDecimal

const val maxComplexPathSize = 4

private fun findPathInternal(swapRatio: Map<String, BigDecimal>, amount: Double, set: ArrayList<String>,
                             src: String, des: String, results: ArrayList<Path>, threshold: Double) {
    if (set.isNotEmpty() && src == des) {
        if (amount >= threshold) {
            val path = Path(amount, ArrayList(set))
            results.add(path)
        }
        return
    }
    if (set.size >= 4) {
        return
    }
    for (pair in swapRatio) {
        val split = pair.key.split("_")
        val from = split[0]
        val to = split[1]
        if (set.contains(to)) {
            continue
        }
        if (from != src) {
            continue
        }
        if (set.size == 3 && to != des) {
            continue
        }
        val ratio = swapRatio[pair.key] ?: 0

        set.add(to)
        val ratioAmount = amount * ratio.toDouble()
        findPathInternal(swapRatio, ratioAmount, set, to, des, results, threshold)
        set.remove(to)
    }
}

fun findProfitPath(ratio: HashMap<String, BigDecimal>, amount: Double, src: String, des: String, threshold: Double): List<Path> {
    val set = arrayListOf<String>()
    val result = arrayListOf<Path>()
    findPathInternal(ratio, amount, set, src, des, result, threshold)
    result.sortByDescending { it.amount }
    return result
}

fun findBestPath(ratio: Map<String, BigDecimal>, amount: Double, toAmount: Double, src: String, des: String): Path? {
    val set = arrayListOf<String>()
    val result = arrayListOf<Path>()
    findPathInternal(ratio, amount, set, src, des, result, toAmount)
    result.sortByDescending { it.amount }
    return if (result.isNotEmpty()) result[0] else null
}


private fun findAllPathKspInternal(pathList: ArrayList<String>, swapListKsp: ArrayList<SwapInfoKsp>, src: String, cur: String, des: String, results: ArrayList<KspPath>) {
    if (pathList.isNotEmpty() && cur == des) {
        val path = KspPath(ArrayList(pathList), ArrayList(swapListKsp), src, des)
        results.add(path)
        return
    }
    if (pathList.size >= 4) {
        return
    }
    for (pair in SwapInfoKsp.values()) {
        val split = pair.name.split("_")
        val from = split[0]
        val to = split[1]
        if (pathList.contains(to)) {
            continue
        }
        if (from != cur) {
            continue
        }
        if (pathList.size == 3 && to != des) {
            continue
        }
        pathList.add(to)
        swapListKsp.add(pair)
        findAllPathKspInternal(pathList, swapListKsp, src, to, des, results)
        swapListKsp.remove(pair)
        pathList.remove(to)
    }
}

fun findAllPathKsp(src: String, des: String): List<KspPath> {
    val pathList = arrayListOf<String>()
    val swapList = arrayListOf<SwapInfoKsp>()
    val result = arrayListOf<KspPath>()
    findAllPathKspInternal(pathList, swapList, src, src, des, result)
    return if (src == des) {
        result.filter { it.path.size > 2 }  // path 의 크기는 des 를 포함하기 때문에, path 가 1개인 경우를 제외하기
    } else {
        result
    }
}


private fun findAllPathGeneral(pathList: ArrayList<TokenInfo>, swapList: ArrayList<SwapSpec>, src: TokenInfo, cur: TokenInfo, des: TokenInfo, results: ArrayList<GeneralPath>, swapSpecList: List<SwapSpec>) {
    if (pathList.isNotEmpty() && cur == des) {
        val copiedPathList = ArrayList(pathList)
        copiedPathList.add(0, src)
        val path = GeneralPath(copiedPathList, ArrayList(swapList), src, des)
        results.add(path)
        return
    }
    if (pathList.size >= 4) {
        return
    }
    for (pair in swapSpecList) {
        val from = pair.from
        val to = pair.to
        if (pathList.contains(to)) {
            continue
        }
        if (from != cur) {
            continue
        }
        if (pathList.size == 3 && to != des) {
            continue
        }
        pathList.add(to)
        swapList.add(pair)
        findAllPathGeneral(pathList, swapList, src, to, des, results, swapSpecList)
        swapList.remove(pair)
        pathList.remove(to)
    }
}

fun findAllPathDefinix(src: TokenInfo, des: TokenInfo): List<GeneralPath> {
    val pathList = arrayListOf<TokenInfo>()
    val swapList = arrayListOf<SwapSpec>()
    val result = arrayListOf<GeneralPath>()

    val swapSpecList = ArrayList(swapInfoDefinix)
    couplingSwapInfo(swapSpecList)

    findAllPathGeneral(pathList, swapList, src, src, des, result, swapSpecList)
    return if (src == des) {
        result.filter { it.path.size > 2 }  // path 의 크기는 des 를 포함하기 때문에, path 가 1개인 경우를 제외하기
    } else {
        result
    }
}

fun couplingSwapInfo(list: ArrayList<SwapSpec>) {
    for (i in list.indices) {
        val spec = list[i]
        val reverse = spec.rev()
        list.add(reverse)
    }
}

fun findAllPathClaimSwap(src: TokenInfo, des: TokenInfo): List<GeneralPath> {
    val pathList = arrayListOf<TokenInfo>()
    val swapList = arrayListOf<SwapSpec>()
    val result = arrayListOf<GeneralPath>()

    val swapSpecList = ArrayList(swapInfoClaimSwap)
    couplingSwapInfo(swapSpecList)

    findAllPathGeneral(pathList, swapList, src, src, des, result, swapSpecList)
    return if (src == des) {
        result.filter { it.path.size > 2 }  // path 의 크기는 des 를 포함하기 때문에, path 가 1개인 경우를 제외하기
    } else {
        result
    }
}

private fun findComplexPathInternal(currentPath: ComplexPath, swapList: ArrayList<SwapSpec>, src: TokenInfo, cur: TokenInfo?, des: TokenInfo, resultList: ArrayList<ComplexPath>) {
    if (currentPath.path.size >= 1 && cur == des) {
        resultList.add(currentPath.clone())
        return
    }

    if (currentPath.path.size >= maxComplexPathSize) {
        return
    }

    for (swapSpec in swapList) {
        if (cur == null) {
            if (swapSpec.from != src) {
                continue
            } else {
                currentPath.path.add(swapSpec)
                findComplexPathInternal(currentPath, swapList, src, swapSpec.to, des, resultList)
                currentPath.path.remove(swapSpec)
            }
        }

        if (currentPath.path.contains(swapSpec)) {
            continue
        }

        val from = swapSpec.from
        val to = swapSpec.to
        if (from != cur) {
            continue
        }

        if (currentPath.path.size == (maxComplexPathSize -1) && to != des) {
            continue
        }

        currentPath.path.add(swapSpec)
        findComplexPathInternal(currentPath, swapList, src, to, des, resultList)
        currentPath.path.remove(swapSpec)
    }
}


fun findComplexPath(src: TokenInfo, des: TokenInfo): List<ComplexPath> {
    val resultList = arrayListOf<ComplexPath>()

    val currentPath = ComplexPath(arrayListOf(), src, des)
    val complexSwapInfo = arrayListOf<SwapSpec>()
    complexSwapInfo.addAll(swapInfoKsp)
    complexSwapInfo.addAll(swapInfoDefinix)
    complexSwapInfo.addAll(swapInfoClaimSwap)
    complexSwapInfo.addAll(swapInfoPala)
    couplingSwapInfo(complexSwapInfo)

    findComplexPathInternal(currentPath, complexSwapInfo, src, null, des, resultList)
    println("ComplexSwapInfo(${complexSwapInfo.size}) [${src}->${des}] size=${resultList.size}")

    return resultList
}
