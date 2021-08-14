(ns framework.core
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.coercion :as coercion]
   [reitit.dev.pretty :as pretty]
   [reitit.coercion.spec]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.middleware.multipart :as multipart]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
   (ring/router
    ["/api"
     ["/math" {:get {:parameters {:query {:x int? :y int?}}
                     :responses  {200 {:body {:total int?}}}
                     :handler    (fn [request]
                                   (let [query
                                         (-> request :parameters :query)

                                         {:keys [x y]}
                                         query]
                                     {:status 200
                                      :body {:total (+ x y)}}))}}]]
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
                         multipart/multipart-middleware]}})))

(defn -main
  [& args]
  (jetty/run-jetty #'app {:port 3000 :join? false})
  (println "server running on port 3000"))
