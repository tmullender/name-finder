(ns name-finder.meanings
  (:require [clojure.string :as str]
            [name-finder.filters :refer [length-of-name]]
            [net.cgrand.enlive-html :as html])
  (:import (java.net URL)))

(defn get-wiki [letter]
  (html/html-resource
    (URL. (str "https://en.wikipedia.org/wiki/List_of_biblical_names_starting_with_" letter))))

(defn extract-name-and-meaning [item]
  (let [content (:content item)
        name (first (:content (first content)))
        length (count name)]
    (hash-map (str/upper-case name) (second content) (str/upper-case (subs name 0 (min length-of-name length))) (second content))))

(defn fetch-wiki [letter]
  (let [items (html/select (get-wiki letter) [:div.mw-content-ltr :ul :li])]
    (into {} (map extract-name-and-meaning (filter #(first (:content (first (:content %1)))) items)))))

(defn fetch-wikis
  ([letters] (fetch-wikis {} letters))
  ([acc letters] (if (empty? letters) acc (fetch-wikis (merge acc (fetch-wiki (first letters))) (rest letters)))))
