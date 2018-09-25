(ns telog.telegram
  (:require [clj-http.client :as client]))

(def token
  "669460264:AAEzEA0ys5k-8urpjjjKN5DJvG6ldadS8a8")

(def channel
  "@bomberwatch")

(def url
  (str "https://api.telegram.org/bot" token "/getChat?chat_id=" channel))

(defn getChat []
  (client/get url))