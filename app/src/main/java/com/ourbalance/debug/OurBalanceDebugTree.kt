package com.ourbalance.debug

import timber.log.Timber

class OurBalanceDebugTree: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}
