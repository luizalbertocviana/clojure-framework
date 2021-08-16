(ns framework.handlers.core
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
  [f]
  {:pre-fn f
   :post-fn identity})

(defn post-handler
  [f]
  {:pre-fn identity
   :post-fn f})

(defn around-handler
  [{:keys [pre post]}]
  {:pre-fn pre
   :post-fn post})

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

