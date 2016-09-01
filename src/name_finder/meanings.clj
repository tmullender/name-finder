(ns name-finder.meanings
  (:require [clojure.string :as str]
            [name-finder.filters :refer [length-of-name]]
            [net.cgrand.enlive-html :as html])
  (:import (java.net URL)))

(defn get-wiki 
  "Uses enlive to scrape the wikipedia page for a list of biblical names starting with letter"
  [letter]
  (html/html-resource
    (URL. (str "https://en.wikipedia.org/wiki/List_of_biblical_names_starting_with_" letter))))

(defn extract-name-and-meaning 
  "Extracts the name and meaning from the html list item and returns a map of name and abbreviated name to meaning"
  [item]
  (let [content (:content item)
        name (first (:content (first content)))
        length (count name)]
    (hash-map (str/upper-case name) (second content) (str/upper-case (subs name 0 (min length-of-name length))) (second content))))

(defn fetch-wiki [letter]
  "Fetches the wikipedia page for biblical names starting with letter and returns a map of name to meaning"
  (let [items (html/select (get-wiki letter) [:div.mw-content-ltr :ul :li])]
    (into {} (map extract-name-and-meaning (filter #(first (:content (first (:content %1)))) items)))))

(defn fetch-wikis
  "Fetches the wikipedia page for biblical names starting with all letters and returns a map of name to meaning"
  ([letters] (fetch-wikis {} letters))
  ([acc letters] (if (empty? letters) acc (fetch-wikis (merge acc (fetch-wiki (first letters))) (rest letters)))))
