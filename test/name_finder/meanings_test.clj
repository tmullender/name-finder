(ns name-finder.meanings-test
  (:require [name-finder.meanings :refer [fetch-wikis]]
            [midje.sweet :refer :all]
            [net.cgrand.enlive-html :as html])
  (:import (java.io File)))

(unfinished get-wiki)

(defn my-get-wiki []
  (html/html-resource (File. "test/wiki.htm")))

(fact "Biblical names should be pulled from wikipedia until we find something better"
      (into (sorted-map) (fetch-wikis #{\Z})) => {
                                                  "TAAN" ", breaking down a fig-tree",
                                                  "TAANACH" ", who humbles thee; who answers thee",
                                                  "TAANACH-SHILO" ", breaking down a fig-tree",
                                                  "TABB" ", rings",
                                                  "TABBAOTH" ", rings",
                                                  "TABBATH" ", good; goodness",
                                                  "TABE" ", to beat with loud strokes",
                                                  "TABEAL" ", Tabeel, good God",
                                                  "TABELEL" ", God is good",
                                                  "TABERAH" ", burning",
                                                  "TABERING" ", to beat with loud strokes",
                                                  }
      (provided (name-finder.meanings/get-wiki anything) => (my-get-wiki)))