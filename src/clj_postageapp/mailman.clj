(ns clj-postageapp.mailman
  (:require
    [clj-http.client :as client]
    [clj-postageapp.request :as req])
  (:use [clojure.tools.logging]))

(def ^{:doc "PostageApp API endpoint"} endpoint "https://api.postageapp.com/v.1.0")
(def ^{:doc "PostageApp Project API Key"} api-key "")


(defn ^{:doc "Initialze the Mailman"} init!
  [key]
  (def api-key key))

(defn ^{:doc "Check Initialization, Raise holy hell if we're not ready to go"} init-check!
  []
  (if (= api-key "")
    (throw (Exception. "API Key is blank!"))))

(defn ^{:doc "Get Account Information"} get-account-info
  []
  (init-check!)
  (let [url (str endpoint "/get_account_info.json")
        request (req/build-get-account-info-request api-key)]
    (trace (str "Sending this get account info request: " (:body request)))
    (client/post url request)))

(defn ^{:doc "Send an email message"} send-message
  [params]
  (init-check!)
  (let [url (str endpoint "/send_message.json")
        request (req/build-send-message-request api-key params)]
    (trace (str "Sending this send message request: " (:body request)))
    (client/post url request)))



(comment
  (-> (init! "kDdAPmGZzW4XvhapRkk2PjfjEg2uxKy7"))
  (-> (get-account-info))
  (-> api-key)
)
