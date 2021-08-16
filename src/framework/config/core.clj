(ns framework.config.core
  (:require
   [framework.config.routes :as routes]
   [framework.config.server :as server]))

(def config
  {:routes        routes/config
   :server-config server/config})
