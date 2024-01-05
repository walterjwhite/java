--TRUNCATE TABLE crawl_seed_resourceuris;
--TRUNCATE TABLE resourceuri;
--TRUNCATE TABLE crawl;

INSERT INTO crawl (id, version, breadthFirst, crawlFrequency, maxDepth) VALUES (1, 1, FALSE, 'Daily', 2);

INSERT INTO resourceuri (id, version, uri) VALUES (1, 1, 'http://lxer.com');
INSERT INTO resourceuri (id, version, uri) VALUES (2, 1, 'http://www.techbargains.com');
INSERT INTO resourceuri (id, version, uri) VALUES (3, 1, 'https://dealsea.com');
INSERT INTO resourceuri (id, version, uri) VALUES (4, 1, 'http://pittsurplus.com');

INSERT INTO resourceuri (id, version, uri) VALUES (5, 1, 'https://pittsburgh.craigslist.org/search/zip?hasPic=1&search_distance=15&postal=:1');
INSERT INTO resourceuri (id, version, uri) VALUES (6, 1, 'https://pittsburgh.craigslist.org/search/fua?hasPic=1&search_distance=15&postal=:1');
INSERT INTO resourceuri (id, version, uri) VALUES (7, 1, 'https://pittsburgh.craigslist.org/search/foa?hasPic=1&search_distance=15&postal=:1');
INSERT INTO resourceuri (id, version, uri) VALUES (8, 1, 'https://pittsburgh.craigslist.org/search/sya?hasPic=1&search_distance=15&postal=:1');
INSERT INTO resourceuri (id, version, uri) VALUES (9, 1, 'https://pittsburgh.craigslist.org/search/bia?hasPic=1&search_distance=15&postal=:1');

INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 1);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 2);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 3);

INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 4);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 5);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 6);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 7);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 8);
INSERT INTO Crawl_ResourceURI (crawl_id, seedResourceURIs_id) VALUES (1, 9);

-- filters
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (1, 1, FALSE, '^twitter.com.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (2, 1, FALSE, '^javascript:.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (3, 1, FALSE, '^mailto:.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (4, 1, FALSE, '.*#.*');

INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (5, 1, FALSE, 'maps.google.com/.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (6, 1, FALSE, 'plus.google.com/.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (7, 1, FALSE, 'accounts.google.com/.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (8, 1, FALSE, '(www\\.)?facebook.com/sharer/sharer.php\\?u.*');
INSERT INTO URIFilterConfiguration (id, version, caseinsensitive, pattern) VALUES (9, 1, FALSE, '.*facebook.com/sharer/sharer.php\\?u.*');

COMMIT;
