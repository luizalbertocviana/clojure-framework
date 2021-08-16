(ns framework.core
  (:require
   [framework.handlers.routes :as routes]
   [framework.handlers.server :as server]
   [framework.config.core     :as main]))

(defn -main
  [& args]
  (-> main/config
      routes/handler
      server/handler))
