
<style type="text/css">
    .inheritable {
        background-color: #fff;
        font-family: inherit;
        font-size: inherit;
        line-height: inherit;
        text-decoration: none;
    }
</style>

<script type="text/javascript">
    var myCode;
    $(document).ready(function() {
        myCode = CodeMirror.fromTextArea($('#doc')[0], { 'onChange': function() {
            $('.inheritable').remove();
            line = 0;
            ch = 0;
            for (line = 0; line < myCode.lineCount(); line++) {

                ch = myCode.getLine(line).indexOf('Hello');

                while (ch > -1) {
                    
                    widget = $('<a class="inheritable" href="http://www.google.com">Hello</a>')[0];
                    pos = {'ch': ch, 'line': line};
                    myCode.addWidget(pos, widget);

                    ch = myCode.getLine(line).indexOf('Hello', ch + 1);
                }
            }
        } });
        
    });
</script>

<textarea id="doc">
The First People is a document(6955 kHz) by Seamus Wiles that was published in 1897. The book describes a race of humans that evolved before the dinosaurs. Only one edition of the book is believed to have been printed.
Edward Markham gives Peter Bishop a rare copy of the book so the science team can start solving the puzzling broadcast of numbers over high frequency radio channels.

Once the team begins to review the book, they learn the author's claim that a cataclysm completely decimated the First People, and any historical record they may have left.
The ancient humans were technologically advanced and knew the source of all creation and destruction, which they called The Vacuum. Wiles claims that time was measured in months with widely varying numbers of days, i.e. 12, 34, 17, 9, 15, 8, 42, 40, etc....

Peter suspects that the Numbers Broadcasts correspond exactly with the numbers in the book, matching the calendar. Astrid doesn't buy the claim in the book that an ancient people evolved before dinosaurs, and then vanished without a trace.
Walter has no issue with justifying the disappearance of the First People - history is full of mass extinction events.In Reciprocity, three more copies of the book are found by Massive Dynamic.
The three books were written in different languages by three different authors and published within two years of each other. Brandon claimed that the books were not much different from one another paper. 

He also told Nina that William Bell made the same search a few years ago.In Concentrate And Ask Again, Nina Sharp reviews the multiple copies of the book that have been quietly secured over the years and stored at Massive Dynamic.
It dawns on her that the letters in the name of each author are identical. She manipulates the letters and finds the name of an old friend. She promptly visits Sam Weiss and confronts him with her concerns.
In 6:02 AM EST, Nina shares what she knows with Olivia about the collection of First People books from around the planet - and the connection they have with Sam Weiss and the Wave Sink Device.
In The Last Sam Weiss, Sam explains that The First People books are based on an ancient manuscript discovered by the his great-great-great-great-grandfather, Sam Weiss. 

Interim generations continued to search for additional ancient information, before the books were written by the fifth Sam Weiss in the late-1890's. Information from the ancient manuscript about gaining entry into the Wave Sink Device once it was activated was not published in the books.
Sam possesses those ancient writings and uses the knowledge to determine the missing component to the ancient device - Olivia.In The Day We Died, Walter explains that a time paradox is responsible for the matter at hand. At some point after May 2026,
they will send the device that destroyed one universe, and is devastating this universe, into the Kappa radiation wormhole in Central Park and back in time 250 million years.
Peter returns to this time period from 2026 and tells everyone that Walter is/was/will be "The First People", along with maybe Ella and Astrid.
</textarea>