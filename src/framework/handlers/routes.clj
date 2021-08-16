(ns framework.handlers.routes
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.coercion :as coercion]
   [reitit.dev.pretty :as pretty]
   [reitit.coercion.spec]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.middleware.multipart :as multipart]
   [reitit.ring.middleware.muuntaja :as muuntaja]))

(defn handler
  [state]
  (assoc state :router-instance
         (ring/ring-handler
          (ring/router (-> state :routes)
                       {:exception pretty/exception
                        :data {:coercion reitit.coercion.spec/coercion
                               :muuntaja m/instance
                               :middleware [parameters/parameters-middleware
                                            muuntaja/format-negotiate-middleware
                                            muuntaja/format-response-middleware
                                            exception/exception-middleware
                                            muuntaja/format-request-middleware
                                            coercion/coerce-response-middleware
                                            coercion/coerce-request-middleware
                                            multipart/multipart-middleware]}}))))
