package com.example.LogisticCompany.model.logisticCompany;

/**
 * LT = Before ("<")
 * EQ = On ("=")
 * MT = After (">")
 * BT = Between ("between X and Y") - REQUIRES 2 arguments to be specified (e.g, 'fromDate' and 'toDate')
 */
public enum FilterType {
    LT,
    EQ,
    MT,
    BT
}
