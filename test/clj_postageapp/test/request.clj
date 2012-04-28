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

(comment
  (-> data/test-api-key-two)
)
