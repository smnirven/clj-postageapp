(ns clj-postageapp.test.request
  (:use [clj-postageapp.request]
        [clojure.test]
        [midje.sweet]))
(facts "check base request body map builder"
  (build-base-request-map "abc") => {"api_key" "abc"})

(facts "check get account info request body builder"
  (build-get-account-info-body "abc132") => "{\"api_key\":\"abc132\"}")
