package com.neocryptolabs.core.util

object Constants {
	const val kspAbi = "[{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"teamWallet\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getExchangeImplementation\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"implementation\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"nextOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_newExImp\",\"type\":\"address\"}],\"name\":\"_setExchangeImplementation\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"halfLife\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"miningAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"poolExist\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"teamAward\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"teamRatio\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"pools\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"createFee\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_newImp\",\"type\":\"address\"}],\"name\":\"_setImplementation\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"unfreezeBlock\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"exchangeImplementation\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"minableBlock\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"}],\"name\":\"tokenToPool\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"_implementation\",\"type\":\"address\"},{\"name\":\"_exchangeImplementation\",\"type\":\"address\"},{\"name\":\"_miningAmount\",\"type\":\"uint256\"},{\"name\":\"_halfLife\",\"type\":\"uint256\"},{\"name\":\"_minableBlock\",\"type\":\"uint256\"},{\"name\":\"_unfreezeBlock\",\"type\":\"uint256\"},{\"name\":\"_teamRatio\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"payable\":true,\"stateMutability\":\"payable\",\"type\":\"fallback\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"holder\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"}, {\"constant\":false, \"inputs\": [{\"name\":\"token\",\"type\":\"address\"}, {\"name\":\"amount\",\"type\":\"uint256\"}, {\"name\":\"path\",\"type\":\"address[]\"}], \"name\": \"exchangeKlayPos\", \"outputs\": [], \"payable\":true,\"stateMutability\":\"payable\",\"type\":\"function\"}, {\"constant\":false, \"inputs\": [{\"name\":\"tokenA\",\"type\":\"address\"}, {\"name\":\"amountA\",\"type\":\"uint256\"}, {\"name\":\"tokenB\",\"type\":\"address\"}, {\"name\":\"amountB\",\"type\":\"uint256\"}, {\"name\":\"path\",\"type\":\"address[]\"}], \"name\": \"exchangeKctPos\", \"outputs\": [], \"stateMutability\":\"nonpayable\",\"type\":\"function\"}, {\"constant\":false, \"inputs\": [{\"name\":\"tokenA\",\"type\":\"address\"}, {\"name\":\"amountA\",\"type\":\"uint256\"}, {\"name\":\"tokenB\",\"type\":\"address\"}, {\"name\":\"amountB\",\"type\":\"uint256\"}, {\"name\":\"path\",\"type\":\"address[]\"}], \"name\": \"exchangeKctNeg\", \"outputs\": [], \"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"spender\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}, {\"constant\": true,\"inputs\": [{\"name\": \"\", \"type\": \"address\"}, {\"name\": \"\", \"type\":\"uint256\"}, {\"name\": \"\", \"type\": \"address\"}], \"name\": \"estimatePos\", \"outputs\": [{\"name\": \"\", \"type\": \"uint256\"}], \"payable\": false, \"stateMutability\": \"view\",  \"type\": \"function\"}]"
	const val kipAbi = "[{\"constant\":true,\"inputs\":[{\"name\":\"interfaceId\",\"type\":\"bytes4\"}],\"name\":\"supportsInterface\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"spender\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"sender\",\"type\":\"address\"},{\"name\":\"recipient\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transferFrom\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"isInitialized\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_version\",\"outputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"version\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"recipient\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"safeTransfer\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"sender\",\"type\":\"address\"},{\"name\":\"recipient\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"safeTransferFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_minter\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"spender\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"decreaseApproval\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"recipient\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"sender\",\"type\":\"address\"},{\"name\":\"recipient\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"safeTransferFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"spender\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"increaseApproval\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"tokenName\",\"type\":\"string\"},{\"name\":\"tokenSymbol\",\"type\":\"string\"}],\"name\":\"setTokenInfo\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"},{\"name\":\"spender\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"recipient\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"safeTransfer\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"minter\",\"type\":\"address\"}],\"name\":\"setMinter\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"},{\"name\":\"minter\",\"type\":\"address\"},{\"name\":\"decimals\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"SetOwner\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"minter\",\"type\":\"address\"}],\"name\":\"SetMinter\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"symbol\",\"type\":\"string\"}],\"name\":\"SetTokenInfo\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"holder\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"}]"
	const val exchangeAbi = "[{\"constant\": true,\"inputs\": [{\"name\": \"\", \"type\": \"address\"}, {\"name\": \"\", \"type\":\"uint256\"}], \"name\": \"estimatePos\", \"outputs\": [{\"name\": \"\", \"type\": \"uint256\"}], \"payable\": false, \"stateMutability\": \"view\",  \"type\": \"function\"},{\"constant\": true,\"inputs\": [{\"name\": \"\", \"type\": \"address\"}, {\"name\": \"\", \"type\":\"uint256\"}], \"name\": \"estimateNeg\", \"outputs\": [{\"name\": \"\", \"type\": \"uint256\"}], \"payable\": false, \"stateMutability\": \"view\",  \"type\": \"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"fee\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}, {\"constant\": true,\"inputs\": [], \"name\": \"tokenA\", \"outputs\": [{\"name\": \"\", \"type\": \"address\"}], \"payable\": false, \"stateMutability\": \"view\",  \"type\": \"function\"},{\"constant\": true,\"inputs\": [], \"name\": \"tokenB\", \"outputs\": [{\"name\": \"\", \"type\": \"address\"}], \"payable\": false, \"stateMutability\": \"view\",  \"type\": \"function\"}, {\"constant\":true,\"inputs\":[],\"name\":\"getCurrentPool\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}, {\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]"
	const val tokenAbi = "[{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]"


	const val definixRouterAddress = "0x4E61743278Ed45975e3038BEDcaA537816b66b5B"
	const val claimRouterAddress = "0xEf71750C100f7918d6Ded239Ff1CF09E81dEA92D"
	const val palaRouterAddress = "0x66EC1B0C3bf4C15a76289ac36098704aCD44170F"
	const val definixRouterAbi = "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_factory\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_WETH\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[],\"name\":\"WETH\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"tokenA\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"tokenB\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amountADesired\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountBDesired\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountAMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountBMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"addLiquidity\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountA\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountB\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"token\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amountTokenDesired\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountTokenMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETHMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"addLiquidityETH\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountToken\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETH\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"}],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"factory\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOut\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"reserveIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"reserveOut\",\"type\":\"uint256\"}],\"name\":\"getAmountIn\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"reserveIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"reserveOut\",\"type\":\"uint256\"}],\"name\":\"getAmountOut\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOut\",\"type\":\"uint256\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOut\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"}],\"name\":\"getAmountsIn\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"}],\"name\":\"getAmountsOut\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountA\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"reserveA\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"reserveB\",\"type\":\"uint256\"}],\"name\":\"quote\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountB\",\"type\":\"uint256\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"tokenA\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"tokenB\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountAMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountBMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"removeLiquidity\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountA\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountB\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"token\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountTokenMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETHMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"removeLiquidityETH\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountToken\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETH\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"token\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountTokenMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETHMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"removeLiquidityETHSupportingFeeOnTransferTokens\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountETH\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"token\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountTokenMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETHMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"approveMax\",\"type\":\"bool\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"removeLiquidityETHWithPermit\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountToken\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETH\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"token\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountTokenMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountETHMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"approveMax\",\"type\":\"bool\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"removeLiquidityETHWithPermitSupportingFeeOnTransferTokens\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountETH\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"tokenA\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"tokenB\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountAMin\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountBMin\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"approveMax\",\"type\":\"bool\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"removeLiquidityWithPermit\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amountA\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountB\",\"type\":\"uint256\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOut\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapETHForExactTokens\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOutMin\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapExactETHForTokens\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOutMin\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapExactETHForTokensSupportingFeeOnTransferTokens\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountOutMin\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapExactTokensForETH\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountOutMin\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapExactTokensForETHSupportingFeeOnTransferTokens\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountOutMin\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapExactTokensForTokens\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountIn\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountOutMin\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapExactTokensForTokensSupportingFeeOnTransferTokens\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOut\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountInMax\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapTokensForExactETH\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amountOut\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amountInMax\",\"type\":\"uint256\"},{\"internalType\":\"address[]\",\"name\":\"path\",\"type\":\"address[]\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"}],\"name\":\"swapTokensForExactTokens\",\"outputs\":[{\"internalType\":\"uint256[]\",\"name\":\"amounts\",\"type\":\"uint256[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"stateMutability\":\"payable\",\"type\":\"receive\"}]"
	const val pairAbi = "[{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"owner\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"sender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount0\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount1\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"}],\"name\":\"Burn\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"sender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount0\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount1\",\"type\":\"uint256\"}],\"name\":\"Mint\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"sender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount0In\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount1In\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount0Out\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount1Out\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"}],\"name\":\"Swap\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint112\",\"name\":\"reserve0\",\"type\":\"uint112\"},{\"indexed\":false,\"internalType\":\"uint112\",\"name\":\"reserve1\",\"type\":\"uint112\"}],\"name\":\"Sync\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"constant\":true,\"inputs\":[],\"name\":\"DOMAIN_SEPARATOR\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"MINIMUM_LIQUIDITY\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"PERMIT_TYPEHASH\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"}],\"name\":\"burn\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amount0\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amount1\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"internalType\":\"uint8\",\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"factory\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getReserves\",\"outputs\":[{\"internalType\":\"uint112\",\"name\":\"_reserve0\",\"type\":\"uint112\"},{\"internalType\":\"uint112\",\"name\":\"_reserve1\",\"type\":\"uint112\"},{\"internalType\":\"uint32\",\"name\":\"_blockTimestampLast\",\"type\":\"uint32\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"_token0\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_token1\",\"type\":\"address\"}],\"name\":\"initialize\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"kLast\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"}],\"name\":\"mint\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"liquidity\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"nonces\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"owner\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"deadline\",\"type\":\"uint256\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"}],\"name\":\"permit\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"price0CumulativeLast\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"price1CumulativeLast\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"}],\"name\":\"skim\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amount0Out\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amount1Out\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"bytes\",\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"swap\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"sync\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"token0\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"token1\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transferFrom\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"

	const val estimator = "0x2f7290B93c7362cA0D64AfB660ff1aB3fab150Cf"
	const val estimatorAbi = "[{\"constant\":false,\"inputs\":[{\"name\":\"token\",\"type\":\"string\"}],\"name\":\"getToken\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"}],\"name\":\"getExchange\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"string\"},{\"name\":\"to\",\"type\":\"string\"}],\"name\":\"getExchangeByName\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"token\",\"type\":\"address\"}],\"name\":\"setToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"string\"},{\"name\":\"to\",\"type\":\"string\"},{\"name\":\"exchange\",\"type\":\"address\"}],\"name\":\"setExchange\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"path\",\"type\":\"string[]\"}],\"name\":\"pushKdaiPath\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"path\",\"type\":\"string[]\"}],\"name\":\"pushKusdtPath\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"path1\",\"type\":\"string[]\"}],\"name\":\"isExistKdaiPath\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"path1\",\"type\":\"string[]\"}],\"name\":\"isExistKusdtPath\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"clearKdaiPath\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"clearKusdtPath\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"doEstimateKdai\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"doEstimateKusdt\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"path\",\"type\":\"string[]\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"estimate\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]"

	const val complexSwap = "0x85857Ce7B1c13d7C7f6CAb610964E44bb5eD53ef"
	const val complexSwapAbi = "[{\"inputs\":[{\"name\":\"swap_list\",\"type\":\"tuple[]\",\"components\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"dex\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"address[]\"},{\"name\":\"exchanges\",\"type\":\"address[]\"}]},{\"name\":\"from\",\"type\":\"string\"},{\"name\":\"to\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"swap\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"user\",\"type\":\"address\"}],\"name\":\"addWhite\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"

	const val complexEstimator = "0x43BCa949a7F799E4453260818AAe52a7E6E9ceE3"
	const val complexEstimatorAbi = "[{\"inputs\":[{\"name\":\"swap_list\",\"type\":\"tuple[]\",\"components\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"dex\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"address[]\"},{\"name\":\"exchanges\",\"type\":\"address[]\"}]},{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"estimateBool\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"swap_list\",\"type\":\"tuple[]\",\"components\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"dex\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"address[]\"},{\"name\":\"exchanges\",\"type\":\"address[]\"}]},{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"estimateUint\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"
	const val kfiAbi = "[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"user\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Deposited\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"DisableWhitelist\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"EnableWhitelist\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"profit\",\"type\":\"uint256\"}],\"name\":\"Harvested\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"user\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"profit\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"performanceFee\",\"type\":\"uint256\"}],\"name\":\"KlayfiPaid\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"previousOwner\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"OwnershipTransferred\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"isPaused\",\"type\":\"bool\"}],\"name\":\"PauseChanged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"user\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"profit\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"performanceFee\",\"type\":\"uint256\"}],\"name\":\"ProfitPaid\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"token\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Recovered\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_address\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"whitelist\",\"type\":\"bool\"}],\"name\":\"Whitelisted\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"user\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"withdrawalFee\",\"type\":\"uint256\"}],\"name\":\"Withdrawn\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"KFI\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"UNLOCK_PERIOD\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"balance\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"check\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"claimLaunchpadRewards\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"deposit\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"depositAll\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"depositedAt\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bool\",\"name\":\"disable\",\"type\":\"bool\"}],\"name\":\"disableWhitelist\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"earned\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"endLaunchpadPhase\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getReward\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"harvest\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_token\",\"type\":\"address\"}],\"name\":\"initialize\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_address\",\"type\":\"address\"}],\"name\":\"isWhitelist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"keeper\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"klayfiChef\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"kspHarvested\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"lastPauseTime\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"launchpadRewardReceiver\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"minter\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"paused\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"poolType\",\"outputs\":[{\"internalType\":\"enum PoolConstant.PoolTypes\",\"name\":\"\",\"type\":\"uint8\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"priceShare\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"principalOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_token\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"receiver\",\"type\":\"address\"}],\"name\":\"recoverToken\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"renounceOwnership\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"rewardsToken\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_kfi\",\"type\":\"address\"}],\"name\":\"setKFI\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_keeper\",\"type\":\"address\"}],\"name\":\"setKeeper\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newKlayfiChef\",\"type\":\"address\"}],\"name\":\"setKlayfiChef\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bool\",\"name\":\"_launchpadPhase\",\"type\":\"bool\"}],\"name\":\"setLaunchpadPhase\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_receiver\",\"type\":\"address\"}],\"name\":\"setLaunchpadRewardReceiver\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newMinter\",\"type\":\"address\"}],\"name\":\"setMinter\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bool\",\"name\":\"_paused\",\"type\":\"bool\"}],\"name\":\"setPaused\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_address\",\"type\":\"address\"},{\"internalType\":\"bool\",\"name\":\"_on\",\"type\":\"bool\"}],\"name\":\"setWhitelist\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_zap\",\"type\":\"address\"}],\"name\":\"setZap\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"sharesOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"stakingToken\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"totalShares\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"transferOwnership\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"withdraw\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"withdrawAll\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"withdrawUnderlying\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"withdrawableBalanceOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"zap\",\"outputs\":[{\"internalType\":\"contract IZapKlayswap\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]\n"

	const val complexKlaySwap = "0x8461f4187eDC52ABd9a8abD43cb44B09809d0827"
	const val complexKlaySwapAbi = complexSwapAbi
}

val dexToAddress = mapOf(
		DEX.KLAYSWAP to TokenInfo.KSP.address,
		DEX.DEFINIX to Constants.definixRouterAddress,
		DEX.CLAIMSWAP to Constants.claimRouterAddress,
		DEX.PALA to Constants.palaRouterAddress,
)

val addressToDex = dexToAddress.map { it.value to it.key }.toMap()

val kspAmount = mapOf(
		TokenInfo.KLAY to 1877.736338,
		TokenInfo.KSP to 391.620392,
		TokenInfo.KUSDT to 2200.0,
		TokenInfo.KUSDC to 2200.0,
		TokenInfo.KDAI to 2200.0,
		TokenInfo.KORC to 5498.757232,
		TokenInfo.KXRP to 2711.542093,
		TokenInfo.KETH to 0.66525,
		TokenInfo.KBNB to 5.113974,
		TokenInfo.KBELT to 1443.373411,
		TokenInfo.KWBTC to 0.047629,
		TokenInfo.KAI to 4088.22343,
		TokenInfo.SKAI to 608.151306,
		TokenInfo.PER to 29625.330975,
		TokenInfo.AKLAY to 1864.083467,
		TokenInfo.KSD to 2185.117615,
		TokenInfo.KBUSD to 2214.0,
		TokenInfo.KRNO to 28.659086,
		TokenInfo.FINIX to 14000.0,
		TokenInfo.SIX to 13000.065652,
		TokenInfo.WEMIX to 474.1567,

		TokenInfo.META to 4903.089692,
		TokenInfo.BORA to 2329.784562,
		TokenInfo.CLBK to 153700.101798,
		TokenInfo.HOUSE to 47653.930001,
		TokenInfo.KLEVA to 1428.206715,
		TokenInfo.KOKOA to 32350.72684,
		TokenInfo.MBX to 68.456531,
		TokenInfo.META to 4902.996658,
		TokenInfo.MIX to 15073.30428,
		TokenInfo.MKC to 593.490659,
		TokenInfo.MNR to 408188.563057,
		TokenInfo.MON to 411006.173685,
)

val definixAmount = mapOf(
		TokenInfo.KLAY to 163.249,
		TokenInfo.KSP to 36.4945,
		TokenInfo.KUSDT to 200.0,
		TokenInfo.KDAI to 200.0,
		TokenInfo.KXRP to 257.964,
		TokenInfo.KETH to 0.0713541,
		TokenInfo.KBNB to 0.527763,
		TokenInfo.KWBTC to 0.00507631,
		TokenInfo.FINIX to 1295.49,
		TokenInfo.SIX to 1175.81,
)

val claimAmount = mapOf(
		TokenInfo.KLAY to 163.91075,
		TokenInfo.KSP to 35.07838,
		TokenInfo.KUSDT to 200.0,
		TokenInfo.KUSDC to 200.0,
		TokenInfo.KDAI to 200.0,
		TokenInfo.KXRP to 258.303961,
		TokenInfo.KETH to 0.07137,
		TokenInfo.KWBTC to 0.00506329,
		TokenInfo.KSD to 195.912036,
		TokenInfo.KOKOA to 1133.7672,
		TokenInfo.KBUSD to 198.499,
		TokenInfo.WEMIX to 36.845342,
		TokenInfo.CLA to 78.9237,
		TokenInfo.KORC to 732.81214,
)

val palaAmount = mapOf(
		TokenInfo.KLAY to 163.130517,
		TokenInfo.PALA to 113.186268,
		TokenInfo.KUSDT to 200.0,
		TokenInfo.KUSDC to 200.0,
		TokenInfo.KDAI to 200.0,
		TokenInfo.KXRP to 257.811047,
		TokenInfo.KETH to 0.071208,
		TokenInfo.KWBTC to 0.005064,
		TokenInfo.KBNB to 0.530394,
)

fun getTokenInfoName(address: String) =
		TokenInfo.values().first { it.address == address }.name

// UPDATE DATE: 2021-09-15
enum class TokenInfo(val decimal: Int, val address: String) {
	AKLAY(18, "0x74ba03198fed2b15a51af242b9c63faf3c8f4d34"),
	BORA(18, "0x02cbe46fb8a1f579254a9b485788f2d86cad51aa"),
	CLA(18, "0xcf87f94fd8f6b6f0b479771f10df672f99eada63"),
	CLAM(18, "0xba9725eaccf07044625f1d232ef682216f5371c2"),
	CLBK(18, "0xc4407f7dc4b37275c9ce0f839652b393e13ff3d1"),
	FINIX(18, "0xd51c337147c8033a43f3b5ce0023382320c113aa"),
	HOUSE(18, "0x158beff8c8cdebd64654add5f6a1d9937e73536c"),
	KAI(18, "0xe950bdcfa4d1e45472e76cf967db93dbfc51ba3e"),
	KBELT(18, "0xdfe180e288158231ffa5faf183eca3301344a51f"),
	KBNB(18, "0x574e9c26bda8b95d7329505b4657103710eb32ea"),
	KBUSD(18, "0x210bc03f49052169d5588a52c317f71cf2078b85"),
	KDAI(18, "0x5c74070fdea071359b86082bd9f9b3deaafbe32b"),
	KETH(18, "0x34d21b1e550d73cee41151c77f3c73359527a396"),
	KFI(18, "0xdb116e2dc96b4e69e3544f41b50550436579979a"),
	KLAY(18, "0x0000000000000000000000000000000000000000"),
	KLEVA(18, "0x5fff3a6c16c2208103f318f4713d4d90601a7313"),
	KOKOA(18, "0xb15183d0d4d5e86ba702ce9bb7b633376e7db29f"),
	KORC(18, "0xfe41102f325deaa9f303fdd9484eb5911a7ba557"),
	KRNO(18, "0xd676e57ca65b827feb112ad81ff738e7b6c1048d"),
	KSD(18, "0x4fa62f1f404188ce860c8f0041d6ac3765a72e67"),
	KSP(18, "0xC6a2Ad8cC6e4A7E08FC37cC5954be07d499E7654"),
	KUSDC(6, "0x754288077d0ff82af7a5317c7cb8c444d421d103"),
	KUSDT(6, "0xceE8FAF64bB97a73bb51E115Aa89C17FfA8dD167"),
	KWBTC(8, "0x16d0e1fbd024c600ca0380a4c5d57ee7a2ecbf9c"),
	KXRP(6, "0x9eaefb09fe4aabfbe6b1ca316a3c36afc83a393f"),
	MBX(18, "0xd068c52d81f4409b9502da926ace3301cc41f623"),
	META(18, "0xe815a060b9279eba642f8c889fab7afc0d0aca63"),
	MIX(18, "0xdd483a970a7a7fef2b223c3510fac852799a88bf"),
	MKC(18, "0x119883ee408aa5b9625c5d09a79fa8be9f9f6017"),
	MNR(6, "0x27dcd181459bcddc63c37bab1e404a313c0dfd79"),
	MON(10, "0x648fd38efefb4f97cf2df3ff93eff70e94da0691"),
	PALA(18, "0x7a1cdca99fe5995ab8e317ede8495c07cbf488ad"),
	PER(18, "0x7eee60a000986e9efe7f5c90340738558c24317b"),
	SBWPM(18, "0xf4546e1d3ad590a3c6d178d671b3bc0e8a81e27d"),
	SIX(18, "0xef82b1c6a550e730d8283e1edd4977cd01faf435"),
	SKAI(18, "0x37d46c6813b121d6a27ed263aef782081ae95434"),
	WEMIX(18, "0x5096db80b21ef45230c9e423c373f1fc9c0198dd"),
	WKLAY_CLAIM(18, "0xe4f05A66Ec68B54A58B17c22107b02e0232cC817"),
	WKLAY_DEFINIX(18, "0x5819b6af194A78511c79C85Ea68D2377a7e9335f"),
	WKLAY_PALA(18, "0x2ff5f6dE2287CA3075232127277E53519A77947C"),
}


enum class ApproveTokenInfo(val decimal: Int, val address: String) {
	KLAY(18, "0x0000000000000000000000000000000000000000"),
	KSP(18, "0xc6a2ad8cc6e4a7e08fc37cc5954be07d499e7654"),
	KUSDT(6, "0xceE8FAF64bB97a73bb51E115Aa89C17FfA8dD167"),
	KUSDC(6, "0x754288077d0ff82af7a5317c7cb8c444d421d103"),
	KDAI(18, "0x5c74070fdea071359b86082bd9f9b3deaafbe32b"),
	KORC(18, "0xfe41102f325deaa9f303fdd9484eb5911a7ba557"),
	KXRP(6, "0x9eaefb09fe4aabfbe6b1ca316a3c36afc83a393f"),
	KETH(18, "0x34d21b1e550d73cee41151c77f3c73359527a396"),
	KBNB(18, "0x574e9c26bda8b95d7329505b4657103710eb32ea"),
	KBELT(18, "0xdfe180e288158231ffa5faf183eca3301344a51f"),
	KWBTC(8, "0x16d0e1fbd024c600ca0380a4c5d57ee7a2ecbf9c"),
	KAI(18, "0xe950bdcfa4d1e45472e76cf967db93dbfc51ba3e"),
	SKAI(18, "0x37d46c6813b121d6a27ed263aef782081ae95434"),
	PER(18, "0x7eee60a000986e9efe7f5c90340738558c24317b"),
	AKLAY(18, "0x74ba03198fed2b15a51af242b9c63faf3c8f4d34"),
	KSD(18, "0x4fa62f1f404188ce860c8f0041d6ac3765a72e67"),
	KOKOA(18, "0xb15183d0d4d5e86ba702ce9bb7b633376e7db29f"),
	KBUSD(18, "0x210bc03f49052169d5588a52c317f71cf2078b85"),
	KRNO(18, "0xd676e57ca65b827feb112ad81ff738e7b6c1048d"),
	FINIX(18, "0xd51c337147c8033a43f3b5ce0023382320c113aa"),
	SIX(18, "0xef82b1c6a550e730d8283e1edd4977cd01faf435"),
	WEMIX(18, "0x5096db80b21ef45230c9e423c373f1fc9c0198dd"),

	BORA(18, "0x02cbe46fb8a1f579254a9b485788f2d86cad51aa"),
	CLA(18, "0xcf87f94fd8f6b6f0b479771f10df672f99eada63"),
	CLAM(18, "0xba9725eaccf07044625f1d232ef682216f5371c2"),
	CLBK(18, "0xc4407f7dc4b37275c9ce0f839652b393e13ff3d1"),
	HOUSE(18, "0x158beff8c8cdebd64654add5f6a1d9937e73536c"),
	KFI(18, "0xdb116e2dc96b4e69e3544f41b50550436579979a"),
	KLEVA(18, "0x5fff3a6c16c2208103f318f4713d4d90601a7313"),
	MBX(18, "0xd068c52d81f4409b9502da926ace3301cc41f623"),
	META(18, "0xe815a060b9279eba642f8c889fab7afc0d0aca63"),
	MIX(18, "0xdd483a970a7a7fef2b223c3510fac852799a88bf"),
	MKC(18, "0x119883ee408aa5b9625c5d09a79fa8be9f9f6017"),
	MNR(6, "0x27dcd181459bcddc63c37bab1e404a313c0dfd79"),
	MON(10, "0x648fd38efefb4f97cf2df3ff93eff70e94da0691"),
}

fun getSwapInfoKspAddres(from: String, to: String) =
		SwapInfoKsp.valueOf("${from}_${to}").address

enum class SwapInfoKsp(val address: String) {
	AKLAY_HOUSE("0xafedb9fd20d0007a13dcd685a89825cd2866f0fc"),
	AKLAY_KOKOA("0x8aa58c2ed508f0450a34c45d1c18105b1a27ee79"),
	AKLAY_KSD("0x0d05d0c2eb373e70f879a8d4389033ef6e816c41"),
	BORA_KLAY("0xbbca9b2d17987ace3e98e376931c540270ce71bb"),
	BORA_KSP("0x26d454097139a66695855a5f3900cbfe65aa5590"),
	CLBK_KLAY("0x55a5dcc23a7a697052ab5d881530849ca0efad34"),
	CLBK_KSP("0x480a0b9d002f3325dbbe3a617d997148d466b6b6"),
	HOUSE_AKLAY("0xafedb9fd20d0007a13dcd685a89825cd2866f0fc"),
	HOUSE_KLAY("0x8ad37f3f3fb663a67c7140947f80894a973b789b"),
	KAI_KDAI("0x92e51bf6ec87623beb774f7356629b61005b1586"),
	KAI_KUSDC("0xce6ea2568ed8074836a0fc7f7f72a40bc142b98f"),
	KAI_KUSDT("0x5787492d753d5f59365e2f98e2f18c3ae3bad6e7"),
	KBELT_KLAY("0x157c39202fae6233fec3f8b3bcb2158200d0a863"),
	KBELT_KORC("0x0f14648ed03a4172a0d186da51b66e7e9af6af66"),
	KBNB_KBUSD("0x5da044864a2cbe03546810f6bb2f274a45edb8c6"),
	KBNB_KETH("0x8119f0cec72a26fe23ca1ab076137ea5d8a19d54"),
	KBNB_KLAY("0xe20b9aeacac615da0fdbeb05d4f75e16fa1f6b95"),
	KBNB_KSP("0x7328b85eff28c3068f69fe662632d37d48ba227f"),
	KBUSD_KBNB("0x5da044864a2cbe03546810f6bb2f274a45edb8c6"),
	KBUSD_KUSDT("0x2c4581ea232527be6b1dbd140e1786bc76ccad61"),
	KDAI_KAI("0x92e51bf6ec87623beb774f7356629b61005b1586"),
	KDAI_KLAY("0xa3987cf6c14f1992e8b4a9e23192eb79dc2969b8"),
	KDAI_KORC("0x587a01f81e5c078cd7c03f09f45705530ffb7b94"),
	KDAI_KRNO("0xdf5caf79899407da1c1b31389448861a9846956d"),
	KDAI_KSD("0x797ff7657f998cfa64288d8c0626aa7cc8190c2d"),
	KDAI_KSP("0x64e58f35e9d4e2ab6380908905177ce150aa8608"),
	KDAI_KUSDT("0xc320066b25b731a11767834839fe57f9b2186f84"),
	KDAI_KXRP("0x4b50d0e4f29bf5b39a6234b11753fdb3b28e76fc"),
	KETH_KBNB("0x8119f0cec72a26fe23ca1ab076137ea5d8a19d54"),
	KETH_KLAY("0x27f80731dddb90c51cd934e9bd54bff2d4e99e8a"),
	KETH_KSD("0xcd546688cb62b60debe3860ed31b69f4d8ebce3e"),
	KETH_KSP("0xa8f8f1153523eaedce48cec2ddbe1bcd483d0cd8"),
	KETH_KUSDT("0x029e2a1b2bb91b66bd25027e1c211e5628dbcb93"),
	KETH_KWBTC("0x2a6a4b0c96ca98eb691a5ddcee3c7b7788c1a8e3"),
	KETH_KXRP("0x85ef87815bd7be28bee038ff201db78c7e0ed2b9"),
	KLAY_BORA("0xbbca9b2d17987ace3e98e376931c540270ce71bb"),
	KLAY_CLBK("0x55a5dcc23a7a697052ab5d881530849ca0efad34"),
	KLAY_HOUSE("0x8ad37f3f3fb663a67c7140947f80894a973b789b"),
	KLAY_KBELT("0x157c39202fae6233fec3f8b3bcb2158200d0a863"),
	KLAY_KBNB("0xe20b9aeacac615da0fdbeb05d4f75e16fa1f6b95"),
	KLAY_KDAI("0xa3987cf6c14f1992e8b4a9e23192eb79dc2969b8"),
	KLAY_KETH("0x27f80731dddb90c51cd934e9bd54bff2d4e99e8a"),
	KLAY_KLEVA("0xd5eb5732ef45b82edaee421cbfb3e412b54d1d2f"),
	KLAY_KOKOA("0x4bfcc93fb85c969a590a2e7d7a4ad72f0668aff2"),
	KLAY_KORC("0xe9ddb7a6994bd9cdf97cf11774a72894597d878b"),
	KLAY_KRNO("0x193ce4066aebe1911feb03425d4312a7b6514081"),
	KLAY_KSD("0xd52acc40924c906d3eeab239d6f6c36b612011af"),
	KLAY_KSP("0x34cf46c21539e03deb26e4fa406618650766f3b9"),
	KLAY_KUSDC("0x3bce8d81ac54010bb7ea6e5960f2ded6fc6a7ac5"),
	KLAY_KUSDT("0xd83f1b074d81869eff2c46c530d7308ffec18036"),
	KLAY_KXRP("0x86e614ef51b305c36a0608baa57fcaaa45844d87"),
	KLAY_MBX("0xbfb4528b7096d983f1c3c693274c4c14887aee41"),
	KLAY_META("0xb62c54b1cf3ec237511a56a7b55fffcc40f8eac9"),
	KLAY_MIX("0xa50cec0216c1cee6f90c7d5359444d46315279bd"),
	KLAY_MKC("0xb8c6b5736ab3d4846a3447aaabbadea258e4a63a"),
	KLAY_MNR("0xe641811d4a0c80d1260d4036df54d90559b9ab54"),
	KLAY_MON("0xeedb8802d8fb7ee2324c5e1f09936dd62ace51b0"),
	KLAY_PER("0x3012cc7a5a137362f97ab1fc69e61f927ac70973"),
	KLAY_SKAI("0x0734f80fbc2051e98e6c7cb37e08e067a9630c06"),
	KLAY_WEMIX("0x917eed7ae9e7d3b0d875dd393af93fff3fc301f8"),
	KLEVA_KLAY("0xd5eb5732ef45b82edaee421cbfb3e412b54d1d2f"),
	KLEVA_KSP("0x1f2600199ed4cf0eaeea79f589a657983cbe2ed2"),
	KOKOA_AKLAY("0x8aa58c2ed508f0450a34c45d1c18105b1a27ee79"),
	KOKOA_KLAY("0x4bfcc93fb85c969a590a2e7d7a4ad72f0668aff2"),
	KORC_KBELT("0x0f14648ed03a4172a0d186da51b66e7e9af6af66"),
	KORC_KDAI("0x587a01f81e5c078cd7c03f09f45705530ffb7b94"),
	KORC_KLAY("0xe9ddb7a6994bd9cdf97cf11774a72894597d878b"),
	KORC_KSP("0x6dc6bd65638b18057f7e6a2e8f136f3e77cc2038"),
	KORC_KUSDT("0x94f390a8438b5de00b868d3ae47863db90fb92c3"),
	KORC_KXRP("0x805cb5eb7063f132ceaf56b2c7182c897a024a83"),
	KORC_PER("0x1527ac4118a56bd17a5136d73d3999c6b7f8d0d1"),
	KORC_SKAI("0x8ca4010c2a3ff730270559c96e0c94d0cac04491"),
	KRNO_KDAI("0xdf5caf79899407da1c1b31389448861a9846956d"),
	KRNO_KLAY("0x193ce4066aebe1911feb03425d4312a7b6514081"),
	KRNO_KSP("0x2febbaed702b9a1d9f6ffccd67701550ac546115"),
	KSD_AKLAY("0x0d05d0c2eb373e70f879a8d4389033ef6e816c41"),
	KSD_KDAI("0x797ff7657f998cfa64288d8c0626aa7cc8190c2d"),
	KSD_KETH("0xcd546688cb62b60debe3860ed31b69f4d8ebce3e"),
	KSD_KLAY("0xd52acc40924c906d3eeab239d6f6c36b612011af"),
	KSD_KSP("0x29068890cc2afefbb5a5725681ca71c81e380b46"),
	KSD_KUSDC("0x3cc0099d7d8f18df8b521e15ef46022fcaf6a48c"),
	KSD_KUSDT("0xc9ad2f9c45c9cc3128c898fe55c14a146e7d1d88"),
	KSD_KWBTC("0x6da444c57ffc8a58e52c5caf3975e7cbe91221fe"),
	KSD_KXRP("0x603dc02f640e7c234bb700d5d4320bb78b3cfe46"),
	KSP_BORA("0x26d454097139a66695855a5f3900cbfe65aa5590"),
	KSP_CLBK("0x480a0b9d002f3325dbbe3a617d997148d466b6b6"),
	KSP_KBNB("0x7328b85eff28c3068f69fe662632d37d48ba227f"),
	KSP_KDAI("0x64e58f35e9d4e2ab6380908905177ce150aa8608"),
	KSP_KETH("0xa8f8f1153523eaedce48cec2ddbe1bcd483d0cd8"),
	KSP_KLAY("0x34cf46c21539e03deb26e4fa406618650766f3b9"),
	KSP_KLEVA("0x1f2600199ed4cf0eaeea79f589a657983cbe2ed2"),
	KSP_KORC("0x6dc6bd65638b18057f7e6a2e8f136f3e77cc2038"),
	KSP_KRNO("0x2febbaed702b9a1d9f6ffccd67701550ac546115"),
	KSP_KSD("0x29068890cc2afefbb5a5725681ca71c81e380b46"),
	KSP_KUSDT("0xe75a6a3a800a2c5123e67e3bde911ba761fe0705"),
	KSP_KWBTC("0x85fae50259ebb9a86f49bdbfb8dbaec84a7ed5fe"),
	KSP_KXRP("0xa06b9a38a7b4b91cb5d9b24538296bfb3b97fbf3"),
	KSP_MBX("0x97b4e13114ce2c9bf289be1ffd1268be5b2ed7c2"),
	KSP_META("0xec1e523bfc3912ca740f551a8a3272b59a07591c"),
	KSP_MIX("0xade6684a81a205e4bfc544b51cca448c458c0961"),
	KSP_MKC("0x9b71b6c2a8baf25ab9f750db9f658abd7084c236"),
	KSP_MNR("0xcde284862c89c391ad759fbc207c8b8d8f49fbda"),
	KSP_MON("0x7bd5249b563390cc51f84bd4a90174622cff4c0c"),
	KSP_PER("0x7220bb49206a98ac513a98444f8e337d92c1a630"),
	KSP_SKAI("0x6456acb56f9eeedb976d5d72b60fb31720155b75"),
	KUSDC_KAI("0xce6ea2568ed8074836a0fc7f7f72a40bc142b98f"),
	KUSDC_KLAY("0x3bce8d81ac54010bb7ea6e5960f2ded6fc6a7ac5"),
	KUSDC_KSD("0x3cc0099d7d8f18df8b521e15ef46022fcaf6a48c"),
	KUSDC_KUSDT("0x2e9269b718cc816de6a9e3c27e5bdb0f6a01b0ac"),
	KUSDT_KAI("0x5787492d753d5f59365e2f98e2f18c3ae3bad6e7"),
	KUSDT_KBUSD("0x2c4581ea232527be6b1dbd140e1786bc76ccad61"),
	KUSDT_KDAI("0xc320066b25b731a11767834839fe57f9b2186f84"),
	KUSDT_KETH("0x029e2a1b2bb91b66bd25027e1c211e5628dbcb93"),
	KUSDT_KLAY("0xd83f1b074d81869eff2c46c530d7308ffec18036"),
	KUSDT_KORC("0x94f390a8438b5de00b868d3ae47863db90fb92c3"),
	KUSDT_KSD("0xc9ad2f9c45c9cc3128c898fe55c14a146e7d1d88"),
	KUSDT_KSP("0xe75a6a3a800a2c5123e67e3bde911ba761fe0705"),
	KUSDT_KUSDC("0x2e9269b718cc816de6a9e3c27e5bdb0f6a01b0ac"),
	KUSDT_KWBTC("0x9103beb39283da9c49b020d6546fd7c39f9bbc5b"),
	KUSDT_PER("0xe55f6452c5b04108dcc2e75ae31d6ca141a4f9b7"),
	KUSDT_WEMIX("0x2d803838cb2d40eacb0207ec4e567e2a8886b47f"),
	KWBTC_KETH("0x2a6a4b0c96ca98eb691a5ddcee3c7b7788c1a8e3"),
	KWBTC_KSD("0x6da444c57ffc8a58e52c5caf3975e7cbe91221fe"),
	KWBTC_KSP("0x85fae50259ebb9a86f49bdbfb8dbaec84a7ed5fe"),
	KWBTC_KUSDT("0x9103beb39283da9c49b020d6546fd7c39f9bbc5b"),
	KXRP_KDAI("0x4b50d0e4f29bf5b39a6234b11753fdb3b28e76fc"),
	KXRP_KETH("0x85ef87815bd7be28bee038ff201db78c7e0ed2b9"),
	KXRP_KLAY("0x86e614ef51b305c36a0608baa57fcaaa45844d87"),
	KXRP_KORC("0x805cb5eb7063f132ceaf56b2c7182c897a024a83"),
	KXRP_KSD("0x603dc02f640e7c234bb700d5d4320bb78b3cfe46"),
	KXRP_KSP("0xa06b9a38a7b4b91cb5d9b24538296bfb3b97fbf3"),
	MBX_KLAY("0xbfb4528b7096d983f1c3c693274c4c14887aee41"),
	MBX_KSP("0x97b4e13114ce2c9bf289be1ffd1268be5b2ed7c2"),
	META_KLAY("0xb62c54b1cf3ec237511a56a7b55fffcc40f8eac9"),
	META_KSP("0xec1e523bfc3912ca740f551a8a3272b59a07591c"),
	MIX_KLAY("0xa50cec0216c1cee6f90c7d5359444d46315279bd"),
	MIX_KSP("0xade6684a81a205e4bfc544b51cca448c458c0961"),
	MKC_KLAY("0xb8c6b5736ab3d4846a3447aaabbadea258e4a63a"),
	MKC_KSP("0x9b71b6c2a8baf25ab9f750db9f658abd7084c236"),
	MNR_KLAY("0xe641811d4a0c80d1260d4036df54d90559b9ab54"),
	MNR_KSP("0xcde284862c89c391ad759fbc207c8b8d8f49fbda"),
	MON_KLAY("0xeedb8802d8fb7ee2324c5e1f09936dd62ace51b0"),
	MON_KSP("0x7bd5249b563390cc51f84bd4a90174622cff4c0c"),
	PER_KLAY("0x3012cc7a5a137362f97ab1fc69e61f927ac70973"),
	PER_KORC("0x1527ac4118a56bd17a5136d73d3999c6b7f8d0d1"),
	PER_KSP("0x7220bb49206a98ac513a98444f8e337d92c1a630"),
	PER_KUSDT("0xe55f6452c5b04108dcc2e75ae31d6ca141a4f9b7"),
	SKAI_KLAY("0x0734f80fbc2051e98e6c7cb37e08e067a9630c06"),
	SKAI_KORC("0x8ca4010c2a3ff730270559c96e0c94d0cac04491"),
	SKAI_KSP("0x6456acb56f9eeedb976d5d72b60fb31720155b75"),
	WEMIX_KLAY("0x917eed7ae9e7d3b0d875dd393af93fff3fc301f8"),
	WEMIX_KUSDT("0x2d803838cb2d40eacb0207ec4e567e2a8886b47f"),

}

val swapInfoKsp = ArrayList<SwapSpec>().apply {
	for (swapInfo in SwapInfoKsp.values()) {
		val sp = swapInfo.name.split("_")
		add(SwapSpec(TokenInfo.valueOf(sp[0]), TokenInfo.valueOf(sp[1]), DEX.KLAYSWAP, swapInfo.address))
	}
}
//		listOf(
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KDAI, DEX.KLAYSWAP, "0xc320066b25b731a11767834839fe57f9b2186f84"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.KORC, DEX.KLAYSWAP, "0x6dc6bd65638b18057f7e6a2e8f136f3e77cc2038"),
//    SwapSpec(TokenInfo.KDAI, TokenInfo.KXRP, DEX.KLAYSWAP, "0x4b50d0e4f29bf5b39a6234b11753fdb3b28e76fc"),
//    SwapSpec(TokenInfo.KXRP, TokenInfo.KETH, DEX.KLAYSWAP, "0x85ef87815bd7be28bee038ff201db78c7e0ed2b9"),
//    SwapSpec(TokenInfo.KETH, TokenInfo.KUSDT, DEX.KLAYSWAP, "0x029e2a1b2bb91b66bd25027e1c211e5628dbcb93"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.KDAI, DEX.KLAYSWAP, "0x64e58f35e9d4e2ab6380908905177ce150aa8608"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.KDAI, DEX.KLAYSWAP, "0xa3987cf6c14f1992e8b4a9e23192eb79dc2969b8"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.KDAI, DEX.KLAYSWAP, "0x587a01f81e5c078cd7c03f09f45705530ffb7b94"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.KXRP, DEX.KLAYSWAP, "0xa06b9a38a7b4b91cb5d9b24538296bfb3b97fbf3"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.KXRP, DEX.KLAYSWAP, "0x86e614ef51b305c36a0608baa57fcaaa45844d87"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.KXRP, DEX.KLAYSWAP, "0x805cb5eb7063f132ceaf56b2c7182c897a024a83"),
//    SwapSpec(TokenInfo.KETH, TokenInfo.KLAY, DEX.KLAYSWAP, "0x27f80731dddb90c51cd934e9bd54bff2d4e99e8a"),
//    SwapSpec(TokenInfo.KETH, TokenInfo.KSP, DEX.KLAYSWAP, "0xa8f8f1153523eaedce48cec2ddbe1bcd483d0cd8"),
//    SwapSpec(TokenInfo.KWBTC, TokenInfo.KETH, DEX.KLAYSWAP, "0x2a6a4b0c96ca98eb691a5ddcee3c7b7788c1a8e3"),
//    SwapSpec(TokenInfo.KBNB, TokenInfo.KETH, DEX.KLAYSWAP, "0x8119f0cec72a26fe23ca1ab076137ea5d8a19d54"),
//    SwapSpec(TokenInfo.KBNB, TokenInfo.KSP, DEX.KLAYSWAP, "0x7328b85eff28c3068f69fe662632d37d48ba227f"),
//    SwapSpec(TokenInfo.KBNB, TokenInfo.KLAY, DEX.KLAYSWAP, "0xe20b9aeacac615da0fdbeb05d4f75e16fa1f6b95"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.KLAY, DEX.KLAYSWAP, "0xe9ddb7a6994bd9cdf97cf11774a72894597d878b"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KSP, DEX.KLAYSWAP, "0xe75a6a3a800a2c5123e67e3bde911ba761fe0705"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.KLAY, DEX.KLAYSWAP, "0x34cf46c21539e03deb26e4fa406618650766f3b9"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KLAY, DEX.KLAYSWAP, "0xd83f1b074d81869eff2c46c530d7308ffec18036"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.KBELT, DEX.KLAYSWAP, "0x0f14648ed03a4172a0d186da51b66e7e9af6af66"),
//    SwapSpec(TokenInfo.KWBTC, TokenInfo.KSP, DEX.KLAYSWAP, "0x85fae50259ebb9a86f49bdbfb8dbaec84a7ed5fe"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KWBTC, DEX.KLAYSWAP, "0x9103beb39283da9c49b020d6546fd7c39f9bbc5b"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.KUSDT, DEX.KLAYSWAP, "0x94f390a8438b5de00b868d3ae47863db90fb92c3"),
//    SwapSpec(TokenInfo.KAI, TokenInfo.KUSDT, DEX.KLAYSWAP, "0x5787492d753d5f59365e2f98e2f18c3ae3bad6e7"),
//    SwapSpec(TokenInfo.KAI, TokenInfo.KDAI, DEX.KLAYSWAP, "0x92e51bf6ec87623beb774f7356629b61005b1586"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.KBELT, DEX.KLAYSWAP, "0x157c39202fae6233fec3f8b3bcb2158200d0a863"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.SKAI, DEX.KLAYSWAP, "0x0734f80fbc2051e98e6c7cb37e08e067a9630c06"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.SKAI, DEX.KLAYSWAP, "0x6456acb56f9eeedb976d5d72b60fb31720155b75"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.SKAI, DEX.KLAYSWAP, "0x8ca4010c2a3ff730270559c96e0c94d0cac04491"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.PER, DEX.KLAYSWAP, "0x7220bb49206a98ac513a98444f8e337d92c1a630"),
//    SwapSpec(TokenInfo.KORC, TokenInfo.PER, DEX.KLAYSWAP, "0x1527ac4118a56bd17a5136d73d3999c6b7f8d0d1"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.PER, DEX.KLAYSWAP, "0x3012cc7a5a137362f97ab1fc69e61f927ac70973"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.PER, DEX.KLAYSWAP, "0xe55f6452c5b04108dcc2e75ae31d6ca141a4f9b7"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KUSDC, DEX.KLAYSWAP, "0x2e9269b718cc816de6a9e3c27e5bdb0f6a01b0ac"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.KUSDC, DEX.KLAYSWAP, "0x3bce8d81ac54010bb7ea6e5960f2ded6fc6a7ac5"),
//    SwapSpec(TokenInfo.KUSDC, TokenInfo.KAI, DEX.KLAYSWAP, "0xce6ea2568ed8074836a0fc7f7f72a40bc142b98f"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KSD, DEX.KLAYSWAP, "0xc9ad2f9c45c9cc3128c898fe55c14a146e7d1d88"),
//    SwapSpec(TokenInfo.KDAI, TokenInfo.KSD, DEX.KLAYSWAP, "0x797ff7657f998cfa64288d8c0626aa7cc8190c2d"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.KSD, DEX.KLAYSWAP, "0xd52acc40924c906d3eeab239d6f6c36b612011af"),
//    SwapSpec(TokenInfo.KSD, TokenInfo.KWBTC, DEX.KLAYSWAP, "0x6da444c57ffc8a58e52c5caf3975e7cbe91221fe"),
//    SwapSpec(TokenInfo.KSD, TokenInfo.KUSDC, DEX.KLAYSWAP, "0x3cc0099d7d8f18df8b521e15ef46022fcaf6a48c"),
//    SwapSpec(TokenInfo.KSD, TokenInfo.KXRP, DEX.KLAYSWAP, "0x603dc02f640e7c234bb700d5d4320bb78b3cfe46"),
//    SwapSpec(TokenInfo.KSD, TokenInfo.KETH, DEX.KLAYSWAP, "0xcd546688cb62b60debe3860ed31b69f4d8ebce3e"),
//    SwapSpec(TokenInfo.KSD, TokenInfo.KSP, DEX.KLAYSWAP, "0x29068890cc2afefbb5a5725681ca71c81e380b46"),
//    SwapSpec(TokenInfo.AKLAY, TokenInfo.KSD, DEX.KLAYSWAP, "0x0d05d0c2eb373e70f879a8d4389033ef6e816c41"),
//    SwapSpec(TokenInfo.KBNB, TokenInfo.KBUSD, DEX.KLAYSWAP, "0x5da044864a2cbe03546810f6bb2f274a45edb8c6"),
//    SwapSpec(TokenInfo.KUSDT, TokenInfo.KBUSD, DEX.KLAYSWAP, "0x2c4581ea232527be6b1dbd140e1786bc76ccad61"),
//    SwapSpec(TokenInfo.KDAI, TokenInfo.KRNO, DEX.KLAYSWAP, "0xdf5caf79899407da1c1b31389448861a9846956d"),
//    SwapSpec(TokenInfo.KSP, TokenInfo.KRNO, DEX.KLAYSWAP, "0x2febbaed702b9a1d9f6ffccd67701550ac546115"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.KRNO, DEX.KLAYSWAP, "0x193ce4066aebe1911feb03425d4312a7b6514081"),
//    SwapSpec(TokenInfo.KSD, TokenInfo.KRNO, DEX.KLAYSWAP, "0x5876aa130de74d9d8924e8ff05a0bc4387ee93f0"),
//    SwapSpec(TokenInfo.KLAY, TokenInfo.WEMIX, DEX.KLAYSWAP, "0x917eed7ae9e7d3b0d875dd393af93fff3fc301f8"),
//)

val swapInfoDefinix = listOf(
		SwapSpec(TokenInfo.FINIX, TokenInfo.SIX, DEX.DEFINIX),
		SwapSpec(TokenInfo.FINIX, TokenInfo.KLAY, DEX.DEFINIX),
		SwapSpec(TokenInfo.FINIX, TokenInfo.KSP, DEX.DEFINIX),
		SwapSpec(TokenInfo.FINIX, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.SIX, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.SIX, TokenInfo.KLAY, DEX.DEFINIX),
		SwapSpec(TokenInfo.KLAY, TokenInfo.KETH, DEX.DEFINIX),
		SwapSpec(TokenInfo.KLAY, TokenInfo.KWBTC, DEX.DEFINIX),
		SwapSpec(TokenInfo.KLAY, TokenInfo.KXRP, DEX.DEFINIX),
		SwapSpec(TokenInfo.KETH, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.KWBTC, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.KXRP, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.KLAY, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.KDAI, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.KBNB, TokenInfo.KUSDT, DEX.DEFINIX),
		SwapSpec(TokenInfo.KBNB, TokenInfo.FINIX, DEX.DEFINIX),
)

val swapInfoClaimSwap = listOf(
		SwapSpec(
				TokenInfo.CLA, TokenInfo.KLAY, DEX.CLAIMSWAP
		),
		SwapSpec(TokenInfo.KUSDT, TokenInfo.CLA, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.WEMIX, TokenInfo.CLA, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KETH, TokenInfo.CLA, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KXRP, TokenInfo.CLA, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KWBTC, TokenInfo.CLA, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KBUSD, TokenInfo.CLA, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.WEMIX, TokenInfo.KLAY, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KUSDT, TokenInfo.KLAY, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KETH, TokenInfo.KLAY, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KXRP, TokenInfo.KLAY, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KETH, TokenInfo.KUSDT, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KWBTC, TokenInfo.KETH, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KUSDC, TokenInfo.KUSDT, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KDAI, TokenInfo.KUSDT, DEX.CLAIMSWAP),

		SwapSpec(TokenInfo.WEMIX, TokenInfo.KUSDT, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KOKOA, TokenInfo.KLAY, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KSD, TokenInfo.KLAY, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KLAY, TokenInfo.KORC, DEX.CLAIMSWAP),
		SwapSpec(TokenInfo.KSP, TokenInfo.KLAY, DEX.CLAIMSWAP),

//    CLA_KFI,
//    CLA_KSTA
//    KOKOA_CLA,
//    KSD_CLA
//    AKLAY_CLA,
//    KLEVA_CLA,
//    KBT_KLAY,
//    WEMIX_KLEVA,
//    KFI_KLAY,
//    KFI_KSTA,
//    AKLAY_KLAY,
)

val swapInfoPala = listOf(
		SwapSpec(TokenInfo.KLAY, TokenInfo.PALA, DEX.PALA),
		SwapSpec(TokenInfo.KUSDT, TokenInfo.PALA, DEX.PALA),
		SwapSpec(TokenInfo.KBNB, TokenInfo.KUSDT, DEX.PALA),
		SwapSpec(TokenInfo.KXRP, TokenInfo.KUSDT, DEX.PALA),
		SwapSpec(TokenInfo.KWBTC, TokenInfo.KUSDT, DEX.PALA),
		SwapSpec(TokenInfo.KETH, TokenInfo.KUSDT, DEX.PALA),
		SwapSpec(TokenInfo.KLAY, TokenInfo.KUSDT, DEX.PALA),
		SwapSpec(TokenInfo.KUSDC, TokenInfo.KUSDT, DEX.PALA),
		SwapSpec(TokenInfo.KDAI, TokenInfo.KUSDT, DEX.PALA),
)

enum class DEX {
	KLAYSWAP,
	DEFINIX,
	CLAIMSWAP,
	EKLIPSE,
	PALA
}

data class SwapSpec(
		val from: TokenInfo,
		val to: TokenInfo,
		val dex: DEX,
		val address: String = "",
) {
	fun rev() =
			SwapSpec(
					to,
					from,
					dex,
					address
			)

	fun toPath() =
			listOf(from, to)
					.map {
						if (it == TokenInfo.KLAY) {
							convertKlay(dex)
						} else {
							it
						}
					}
					.map {
						it.address
					}

	fun toKsp() =
			SwapInfoKsp.valueOf("${from.name}_${to.name}")
}

private fun convertKlay(dex: DEX) =
		when (dex) {
			DEX.PALA -> {
				TokenInfo.WKLAY_PALA
			}
			DEX.DEFINIX -> {
				TokenInfo.WKLAY_DEFINIX
			}
			DEX.CLAIMSWAP -> {
				TokenInfo.WKLAY_CLAIM
			}
			DEX.EKLIPSE, DEX.KLAYSWAP -> {
				TokenInfo.KLAY
			}
		}