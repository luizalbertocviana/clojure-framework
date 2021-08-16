(ns framework.routes.math)

(defn handler
  [request]
  (let [query
        (-> request :parameters :query)

        {:keys [x y]}
        query]
    {:status 200
     :body {:total (+ x y)}}))
