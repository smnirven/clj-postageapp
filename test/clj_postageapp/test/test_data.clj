(ns clj-postageapp.test.test-data)

(def ^{:doc "Test API key one"} test-api-key-one "abc132")
(def ^{:doc "Test API key two"} test-api-key-two "abcxyz")

(def test-send-message-params
  {:recipients ["bob@me.com"]
   :subject "testy mctestum"
   :from "no-reply@test.com"
   :template "test-template"})

(defn test-send-message-result
  [api-key]
  {:content-type :json
   :socket-timeout 1000
   :connection-timeout 1000
   :accept :json
   :body (str "{\"api_key\" : \"" api-key "\",
                \"arguments\" : {
                  \"recipients\" : [\"bob@me.com\"],
                  \"headers\" : {
                    \"subject\" : \"testy mctestum\",
                    \"from\" : \"no-reply@test.com\"
                  },
                  \"template\" : \"test-template\"
                }
              }")})
(defn test-send-message-body-map
  [api-key]
  {"api_key" api-key
   "arguments"
   {"recipients"
    ["bob@me.com"]
    "headers"
    {"subject" "testy mctestum"
     "from" "no-reply@test.com"}
    "template" "test-template"}})

(comment
  (-> (test-send-message-body-map "abc"))
  )
