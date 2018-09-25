(ns telog.server
  (:require [compojure.route]
            [clojure.stacktrace]
            [ring.util.response]
            [immutant.web :as web]
            [compojure.core :as compojure]
            [compojure.route :as croute]
            [ring.adapter.jetty :as jetty]
            [telog.core :as telog]))

(compojure/defroutes app
  (compojure/GET "/" [] (telog/render-posts))
  (compojure/GET "/posts/:id" [id] (try (telog/render-post id)
                                        (catch Exception e
                                          {:status 404 :body "Not Found"})))
  (croute/not-found {:status 400 :body "Invalid data"}))

(defn -main [& args]
  (let [args-map (apply array-map args)
        port-str (or (get args-map "-p")
                     (get args-map "--port")
                     "8080")]
    (println "Starting web server on port" port-str)
    (web/run #'app {:port (Integer/parseInt port-str)})))
