(ns clj-postageapp.response
  (:use [clojure.tools.logging]
        [cheshire.core]))

(defn ^{:doc "Parse an api request's response"} parse
  [resp]
  (trace (str "Received this response: " resp))
  (merge resp {:body (parse-string (:body resp))}))
