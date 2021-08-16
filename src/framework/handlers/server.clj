(ns framework.handlers.server
  (:require
   [ring.adapter.jetty :as jetty]))

(defn handler
  [state]
  (assoc state :server-instance
         (jetty/run-jetty (-> state :router-instance)
                          (-> state :server-config))))
