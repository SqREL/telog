(ns telog.generator
  (:require [clojure.java.io :as io])
  (:import
    [org.joda.time DateTime]
    [org.joda.time.format DateTimeFormat]))

(defn newpost [& name-dirty]
  (let [name (clojure.string/join " " name-dirty)
        post-slug (clojure.string/lower-case (clojure.string/join "-" name-dirty))]
    (spit (str "resources/posts/" (.getTime (new java.util.Date)) "_" post-slug "_post.md")
          (str "Author: Vasyl Melnychuk\n"
               "Date: " (.print (DateTimeFormat/forPattern "dd.MM.YYYY") (.getTime (new java.util.Date))) "\n"
               "Title: " name "\n\n"
               "Body"))))