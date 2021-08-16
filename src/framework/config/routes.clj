(ns framework.config.routes
  (:require
   [framework.routes.math :as math]))

(def config
  ["/api"
   ["/math" {:get {:parameters {:query {:x int? :y int?}}
                   :responses  {200 {:body {:total int?}}}
                   :handler    math/handler}}]])
