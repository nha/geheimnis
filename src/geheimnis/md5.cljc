(ns geheimnis.md5
  #?(:cljs (:require [goog.crypt.Md5]
                      [goog.crypt.Hash]
                      [goog.crypt]))
  #?(:clj (:import [java.security MessageDigest]
                   [java.math BigInteger])))

(defn encode [s]
  #?(:clj
     (let [algorithm (MessageDigest/getInstance "MD5")
           raw       (.digest algorithm (.getBytes s))]
       (format "%032x" (BigInteger. 1 raw)))
     :cljs
     (let [bytes (goog.crypt/stringToUtf8ByteArray s)
           md5-digester (goog.crypt.Md5.)
           hashed (do
                    (.update md5-digester bytes)
                    (.digest md5-digester))]
       (goog.crypt/byteArrayToHex hashed))))
