(ns clj-postageapp.mailman
  (:require
    [clj-http.client :as client]
    [clj-postageapp.request :as req]
    [clj-postageapp.response :as resp])
  (:use [clojure.tools.logging]))

(def ^{:doc "PostageApp API endpoint"} endpoint "https://api.postageapp.com/v.1.0")
(def ^{:doc "PostageApp Project API Key"} api-key "")


(defn ^{:doc "Initialze the Mailman"} init!
  [key]
  (def api-key key))

(defn- ^{:doc "Check Initialization, Raise holy hell if we're not ready to go"} init-check!
  []
  (if (= api-key "")
    (throw (Exception. "API Key is blank!"))))

(defn- send-api-request
  [url request]
  (trace (str "Sending this request: " request " To this URL: " url))
  (let [raw-response (client/post url request)
        response (resp/parse raw-response)]
    response))

(defn ^{:doc "Get Account Information"} get-account-info
  []
  (init-check!)
  (let [url (str endpoint "/get_account_info.json")
        request (req/build-get-account-info-request api-key)]
    (send-api-request url request)))

(defn ^{:doc "Send an email message"} send-message
  [params]
  (init-check!)
  (let [url (str endpoint "/send_message.json")
        request (req/build-send-message-request api-key params)]
    (send-api-request url request)))




;; For you slime/swank folks, here's a little inline usage example
(comment
  (-> (init! "YOUR POSTAGEAPP API KEY"))
  (-> (get-account-info))
  (-> (send-message
       {:recipients ["bob@test.com"]
        :subject "Greetings"
        :from "no-reply@test.com"
        :template "postageapp_email_template"
        :variables {"first_name" "Jimbo" "last_name" "Baggins"}}))
)
