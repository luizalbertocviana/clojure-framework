(ns framework.handler
  (:require
   [clojure.spec.alpha :as s]))

(s/def ::handler
  (s/keys :req-un [::pre-fn
                   ::post-fn]))

(s/def ::state-fn fn?)

(s/def ::pre-fn ::state-fn)

(s/def ::post-fn ::state-fn)

(s/def ::state map?)

(s/def ::handler-seq
  (s/and seq?
         (s/coll-of ::state-fn)))

(defn pre-handler
  [kw f]
  {:pre-fn #(update % kw f)
   :post-fn identity})

(defn post-handler
  [kw f]
  {:pre-fn identity
   :post-fn #(update % kw f)})

(defn around-handler
  [kw {:keys [pre post]}]
  {:pre-fn #(update % kw pre)
   :post-fn #(update % kw post)})

(defn handle
  [handler-seq initial-state]
  (let [intermediate-state
        (reduce (fn [state handler]
                  (apply (:pre-fn handler) [state]))
                initial-state
                handler-seq)

        final-state
        (reduce (fn [state handler]
                  (apply (:post-fn handler) [state]))
                intermediate-state
                (reverse handler-seq))]
    final-state))

