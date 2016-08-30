(ns name-finder.filters)

(def length-of-name 4)

(def invalid-first-letters `(\T, \R, \J, \E, \C, \W, \X))

(def mandatory-letters #{\R, \A, \H, \J, \E, \L})

(defn filter-length [name]
  (= (count name) length-of-name))

(defn filter-first-letter [name]
  (let [is-first-letter #(= %1 (first name))]
    (not-any? is-first-letter invalid-first-letters)))

(defn contains-one-mandatory [name]
  (some mandatory-letters name))

(defn name-filter [name]
  (and (filter-first-letter name)
       (filter-length name)
       (contains-one-mandatory name)))
