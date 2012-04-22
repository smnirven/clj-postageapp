(ns clj-postageapp.request
  (:use [cheshire.core]))

(defn ^{:doc "Build base request map"}
  build-base-request-map
  [api-key]
    {"api_key" api-key})

(defn ^{:doc "Build json request body for get account info API request"}
  build-get-account-info-body
  [api-key]
  (generate-string (build-base-request-map api-key)))

(defn ^{:doc "Build json request body for send message API request"}
  build-send-message-body
  []
  )

(comment
  (-> (generate-string (build-base-request-map "asdfjkl1234")))
)

