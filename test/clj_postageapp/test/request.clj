(ns clj-postageapp.test.request
  (:use [clj-postageapp.request]
        [clojure.test]
        [midje.sweet]
        [clj-postageapp.test.test-data :as data]))
(facts "check base request body map builder"
  (build-base-request-map "abc") => (just {"api_key" "abc"}))

(facts "check get account info request body builder"
  (build-get-account-info-body-map data/test-api-key-one) =>
  (just {"api_key" data/test-api-key-one}))

(facts "check send message request body builder"
  (build-send-message-body-map
   data/test-api-key-two
   data/test-send-message-params) =>
   (contains {"api_key" data/test-api-key-two
              "arguments" (contains {"recipients" ["bob@me.com"]
                                     "headers" (contains {"subject" "testy mctestum"
                                                          "from" "no-reply@test.com"})
                                     "template" "test-template"})
              "uid" anything}))

(facts "check send message request body builder with custom variables"
  (build-send-message-body-map
   data/test-api-key-two
   data/send-message-custom-vars-params) =>
   (contains {"arguments"
              (contains {"variables"
                         (just {"first_name" "Jimbo"
                                "last_name" "Baggins"})})}))

(facts "check send message full request builder"
  (build-send-message-request
   data/test-api-key-one
   data/test-send-message-params) =>
   (contains {:headers (just {"User-Agent" "CLJ-POSTAGEAPP 0.2.0-alpha"})
              :accept :json
              :content-type :json
              :socket-timeout anything
              :connection-timeout anything}))
(comment
  (-> data/test-api-key-two)
)
