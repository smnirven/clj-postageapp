(ns clj-postageapp.mailman
  (:require
   [clj-http.client :as client])
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
        request-body (str "\"api_key\":\"" api-key "\"")]
    (println "BODY: " request-body)
    (client/post url
                 {:body request-body
                  :content-type :json
                  :socket-timeout 1000
                  :connection-timeout 1000
                  :accept :json})))
(comment
  (-> (init! "kDdAPmGZzW4XvhapRkk2PjfjEg2uxKy7"))
  (-> (get-account-info))
  (-> api-key)
)
