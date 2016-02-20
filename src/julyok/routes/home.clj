(ns julyok.routes.home
  (:require [julyok.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [julyok.db.core :as db]))

(def types {:text "text"
            :photo "photo"})

(def user {:id "a@b.ru" :pass "123"})

(defn get-stories
  []
  (let [stories (db/get-stories)]
    stories))

(defn get-elements
  [id]
  (let [elements (db/get-elements-by-story {:id id})]
    (sort #(compare (:position %1) (:position %2)) elements)))

(defn home-page []
  (layout/render
    "home.html" {:stories (get-stories)}
    ))

(defn admin-home-page
  []
  (layout/render
    "admin_home.html" {:stories (get-stories)}
    ))

(defn login-page
  []
  (layout/render
    "login.html"))

(defn get-story-name
  [id]
  (let [story-info (db/get-story {:id id})]
    (:name (first story-info))))

(defn story-page
  [id]
  (let [elements (get-elements id)
        story-name (get-story-name id)]
    (layout/render
      "story.html" {:elements elements :types types :name story-name})))

(defn about-page []
  (layout/render "about.html"))

(defn login! [request]
  (println request))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/story/:id" [id] (story-page (read-string id)))
  (GET "/about" [] (about-page)))

(defroutes admin-routes
           (GET "/admin" [] (admin-home-page))
           (GET "/admin/" [] (admin-home-page)))


(defroutes login-routes
           (GET "/login" [] (login-page))
           (POST "/login" request [] (login! request)))

