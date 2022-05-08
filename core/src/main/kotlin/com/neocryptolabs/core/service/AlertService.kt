package com.neocryptolabs.core.service

import com.neocryptolabs.core.util.Log
import com.neocryptolabs.core.util.makeTimeLinkMsg
import com.neocryptolabs.core.util.makeTimeMsg
import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackMessage
import org.springframework.stereotype.Service

@Service
class AlertService {
    companion object : Log

    fun sendMsg(content: String, target: com.neocryptolabs.core.alert.SlackTarget = com.neocryptolabs.core.alert.SlackTarget.JBOT1) {
        send(content, target)
    }

    fun sendTimeMsg(content: String, target: com.neocryptolabs.core.alert.SlackTarget = com.neocryptolabs.core.alert.SlackTarget.JBOT1) {
        send(makeTimeMsg(content), target)
    }

    fun sendTimeMentionMsg(identifier: String, content: String, target: com.neocryptolabs.core.alert.SlackTarget = com.neocryptolabs.core.alert.SlackTarget.JBOT1) {
        send(makeTimeMsg("<@${identifier}> ${content}"), target)
    }

    fun sendTimeLinkMsg(content: String, url: String, target: com.neocryptolabs.core.alert.SlackTarget = com.neocryptolabs.core.alert.SlackTarget.JBOT1) {
        send(makeTimeLinkMsg(url, content), target)
    }

    private fun send(content: String, slackTarget: com.neocryptolabs.core.alert.SlackTarget) {
        val username = slackTarget.userName
        val api = SlackApi(slackTarget.webHookUrl)
        api.call(SlackMessage(slackTarget.channelName, username, content))
        logger.debug("Sent message by Slack API. Channel: ${slackTarget.channelName}, User: ${username}, Content: ${content}")
    }
}