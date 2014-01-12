Rough Design explanation:

This function will return the top exact result or top partial result (partial results are rated) for a particular document. It will highlight only an exact match in the case of a full match, or go through the exercise of highlighting all words individually if some are found, but no exact match.

I broke everything up into sentences. While it's possible to do more (I added some generic functions for shortening), for now I'm pretty happy with the sentence summary. Speaking of shortening, there's a dynamic way to shorten a sentence. It 'centers' itself on the first match it finds in the snippet. I weighted more towards the rear side, because I find users when searching for a noun will often describe that noun after it (like in yelp's case "The Deep Dish Pizza was the best I ever had"). And the modifiers for the noun would be caught by the front side, since they're often syntactically close to the noun i.e. "That was the best deep dish pizza I've had in San Francisco after midnight").

PROPERTIES:
Global Properties (Class): All the properties... You can change the text delimiters, the tag, etc. Normally this would be in a props file or DB, but I'm unsure of your runtime environment.

FUNCTIONAL CLASSES:
* Highlighter: Root class... Invokes the highlighter and returns the document snippet with highlight text. This is the best match evaluated by score. In case of a tie, I went with the first instance of the match, over the second.
* Document: The input string (i.e. review) to be broken up. Contains a list of Snippets and a Snippet Comparator to judge how good a snippet is.
* DocumentMatcher: The match function starts with full ordered, exact match, then goes to all words matched in order, all words matched out of order, then progressively gets worse with number of matches.
* Snippet: A sentence that contains the highlighted string (if applicable) and a score for potential return value.
* Query: The Query string for the user. Can be 'n' size. Broken out to a string array of separate terms.
* HighlighterUtility: Utility class. Has a shortener and a RegEx crafter for exact regexs.

TESTS:
Unit Tests were written for all major classes.

CODE VERIFICATION:
FindBugs and EclipseMetrics were both executed. Both came back normal (zero issues). All compilation warnings have also been resolved, and all System.outs are scrubbed.

KNOWN TRADE-OFFS
- I didn't highlight all relevant words in the case of a 'exact' match. I found the exact matches and just highlighted them. I could either highlight each word individually as the partial match functions, or keep it as is as it looks a little neater.
- I didn't add the highlighting tags during the scoring process (although I could have since I was in the string at that point). It would've had better perf, but I felt it broke the integrity of the scoring function. It made a little more OO sense in the other place, even though it'll mean some additional processing of the strings.