function updateLelModeSymbols() {
    CodeMirror.defineMode("lel", function() {
        
        function wordRegexp(words) {
            return new RegExp("^((" + words.join(")|(") + "))", "i");
        }
        var words = [];
        $.each(projectSymbols, function(i, e) {
            words.push(i);
        });
        var keywords = wordRegexp(words);
        
        function tokenLexer(stream) {
            if (stream.eatSpace()) {
                return null;
            }
            var matched = stream.match(keywords);
            if (matched) {
                return 'symbol' + projectSymbols[matched[0]];
            } else {
                stream.next();
                return null;
            }
        }
        
        return {
            token: function(stream) {
                return tokenLexer(stream);
            }
        };
    });
    CodeMirror.defineMIME("text/x-lel", "lel");
}