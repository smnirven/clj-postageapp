(ns clj-postageapp.test.mailman
  (:use [clj-postageapp.mailman]
        [clojure.test]
        [midje.sweet]))

(facts "exception gets raised if a request is called before initialization"
  (get-account-info) => (throws Exception "API Key is blank!"))
