(ns clj-postageapp.request
  (:use [cheshire.core]))

;; How many milliseconds to wait for opening a connection
(def conn-timeout 1000)

;; How many milliseconds to wait for a response once a request has been sent
(def socket-timeout 5000)

;; These request params generally stay the same for all requests
(def default-request-params {:content-type :json
                             :socket-timeout socket-timeout
                             :connection-timeout conn-timeout
                             :accept :json
                             :headers {"User-Agent" "CLJ-POSTAGEAPP 0.2.0-alpha"}})

(defn ^{:doc "Build base request map"}
  build-base-request-map
  [api-key]
    {"api_key" api-key})

(defn ^{:doc "Build request body map for get account info API request"}
  build-get-account-info-body-map
  [api-key]
  (build-base-request-map api-key))

(defn ^{:doc "Build request body map for send message API request"}
  build-send-message-body-map
  [api-key params]
  (let [uid (str (java.util.UUID/randomUUID))
        args {"arguments"
               {"recipients" (:recipients params)
                "headers"
                 {"subject" (:subject params)
                  "from" (:from params)}
                "template" (:template params)
                "variables" (:variables params)}
              "uid" uid}]
    (merge (build-base-request-map api-key) args)))
        
  


(defn ^{:doc "Build full request map for get account info API request"}
  build-get-account-info-request
  [api-key]
  (merge {:body (generate-string (build-get-account-info-body-map api-key))}
         default-request-params))

(defn ^{:doc "Build full request map for send message API request"}
  build-send-message-request
  [api-key params]
  {:pre [(contains? params :recipients)
         (contains? params :subject)
         (contains? params :from)
         (contains? params :template)]}
  (merge {:body (generate-string
                 (build-send-message-body-map api-key params))}
         default-request-params))

(comment
  (-> (generate-string (build-base-request-map "asdfjkl1234"))))

