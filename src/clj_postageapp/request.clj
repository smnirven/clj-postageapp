(ns clj-postageapp.request
  (:use [cheshire.core]))

;; How many milliseconds to wait for opening a connection
(def conn-timeout 1000)

;; How many milliseconds to wait for a response once a request has been sent
(def socket-timeout 1000)

;; These request params generally stay the same for all requests
(def default-request-params {:content-type :json
                             :socket-timeout socket-timeout
                             :connection-timeout conn-timeout
                             :accept :json})

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
  [api-key, params]
  )

(defn ^{:doc "Build full request map for get account info API request"}
  build-get-account-info-request
  [api-key]
  (merge {:body (build-get-account-info-body api-key)}
         default-request-params))

(defn ^{:doc "Build full request map for send message API request"}
  build-send-message-request
  [api-key params]
  (merge {:body (build-send-message-body api-key params)}
         default-request-params))

(comment
  (-> (generate-string (build-base-request-map "asdfjkl1234"))))

