(ns telog.core
  (:require [rum.core :as rum]
            [clojure.java.io :as io]
            [clojure.string :as s])
  (:use markdown.core))

(def styles (slurp (io/resource "public/style.css")))
(def script (slurp (io/resource "public/script.js")))

(defn render-file [file]
  (md-to-html-string-with-meta (slurp file)))

(rum/defc page [opts & children]
  (let [{:keys [title index?]
         :or {title  "rm -rf /Applications"
              index? false}} opts]
    [:html
     [:head
      [:meta {:http-equiv "Content-Type" :content "text/html; charset=UTF-8"}]
      [:title title]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
      [:style {:dangerouslySetInnerHTML {:__html styles}}]]
     [:body
      [:header
       (if index?
         [:h1 title]
         [:h1 [:a {:href "/"} title]])
       [:h4#site_subtitle "Your Software gore magazine"]]
      children
      [:footer
       [:a {:href "https://twitter.com/sqrel"} "Vasilij Melnychuk"]
       [:br]]
      [:script {:dangerouslySetInnerHTML {:__html script}}]]]))

(defn posts []
  (mapv 
    #(render-file %)
    (filter
      #(.isFile %)
      (file-seq (clojure.java.io/file "resources/posts/")))))

(defn post-slug [title]
  (s/lower-case (s/replace title #"\s" "-")))

(rum/defc html-post [{metadata :metadata html :html}]
  (let [title     (first (:title metadata))
        post-slug (post-slug title)]
    [:.post
      [:h2.post-title [:a {:href (str "/posts/" post-slug)} title]]
      [:.post-author
        "by "
        [:i (first (:author metadata))]
        " at " (first (:date metadata))]
      [:.post-body {:dangerouslySetInnerHTML {:__html html}}]]))

(defn render-posts []
  (rum/render-static-markup 
    (page
      {:index? true}
      [:.posts (map #(html-post %) (posts))])))

(defn post [id]
  (first (filter
      #(clojure.string/includes? % id)
      (file-seq (clojure.java.io/file "resources/posts/")))))

(defn render-post [id]
  (rum/render-static-markup
    (page
      {:index? false}
      (html-post (render-file (post id))))))