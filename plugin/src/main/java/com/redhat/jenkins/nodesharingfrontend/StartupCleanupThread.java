package com.redhat.jenkins.nodesharingfrontend;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.Extension;
import hudson.Util;
import hudson.model.Computer;
import hudson.model.listeners.ItemListener;
import hudson.slaves.Cloud;
import hudson.util.OneShotEvent;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.resourcedisposer.AsyncResourceDisposer;
import org.jenkinsci.plugins.resourcedisposer.Disposable;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Startup cleanup thread for SharedNode instances
 *
 * @author pjanouse
 */
//@Extension
//@Restricted(NoExternalUse.class)
public final class StartupCleanupThread {

//    @Initializer(after = InitMilestone.COMPLETED)
//    public static void onCompleted() {
//        LOGGER.finer("[START] StartupCleanupThread.onCompleted()");
//
//        // Make the time consuming operation in the separate thread to not block other listeners
//        new Thread("ForemanStartCleanupThread") {
//            @Override
//            public void run() {
//                runCleanup();
//            }
//        }.start();
//        LOGGER.finer("[COMPLETED] StartupCleanupThread.onCompleted()");
//    }

    // Until resolved JENKINS-37759, then remove this class and use above onComplete()
    @Extension
    public final static class OnLoadedListener extends ItemListener {
        private static final Logger LOGGER = Logger.getLogger(OnLoadedListener.class.getName());
        private static final int SLEEPING_DELAY = 10000;    // in ms
        private transient OneShotEvent executed = null;
        private transient Object executedLock = null;

        private synchronized Object getExecutedLock() {
            if (executedLock == null) {
                executedLock = new Object();
            }
            return executedLock;
        }

        @Override
        public void onLoaded() {
            LOGGER.finer("[START] StartupCleanupThread.OnLoadedListener.onLoaded()");
            synchronized (getExecutedLock()) {
                if (executed == null) {
                    executed = new OneShotEvent();
                }
                if (!executed.isSignaled()) {
                    executed.signal();
                } else {
                    LOGGER.finer("[COMPLETED] StartupCleanupThread.OnLoadedListener.onLoaded() - without a new thread");
                    return;
                }
            }

            // Make the time consuming operation in the separate thread to not block other listeners
//            new Thread("StartupCleanupThread") {
//                @Override
//                public void run() {
//                    runCleanup();
//                }
//            }.start();
            LOGGER.finer("[COMPLETED] StartupCleanupThread.OnLoadedListener.onLoaded() - with a new thread");
        }
    }
}