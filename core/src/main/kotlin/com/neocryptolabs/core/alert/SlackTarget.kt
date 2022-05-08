package com.neocryptolabs.core.alert

enum class SlackTarget(val webHookUrl: String, val userName: String, val channelName: String) {
    JBOT1("https://hooks.slack.com/services/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", "JBOT1", "#jbot1"),
    LJH("https://hooks.slack.com/services/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", "JBOT", "#이지환"),
    IMO("https://hooks.slack.com/services/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", "JBOT", "#이지환"),   // 직접 관리
    KYJ("https://hooks.slack.com/services/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", "JBOT", "#김용준"),
    KCH("https://hooks.slack.com/services/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", "JBOT", "#권창훈"),
    CSY("https://hooks.slack.com/services/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", "JBOT", "#조세영"),
    HDJ("https://hooks.slack.com/services/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", "JBOT", "#한동진"),
    KDY("https://hooks.slack.com/services/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", "JBOT", "#김도영"),
    JBOT2("https://hooks.slack.com/services/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", "AutoTrade", "#jbot2"),
    JBOT1_STATUS("https://hooks.slack.com/services/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", "JBOT1", "#jbot-status"),
    ;
}