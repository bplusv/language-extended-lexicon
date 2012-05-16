<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
            <script type="text/javascript">
                function getSelectedText() {
                    if(window.getSelection) { return window.getSelection(); }
                    else if(document.getSelection) { return document.getSelection(); }
                    else {
                        var selection = document.selection && document.selection.createRange();
                        if(selection.text) { return selection.text; }
                        return false;
                    }
                    return false;
                }
                
                $(document).ready(function(){
                    var infoBubble;
                    $('#document').mouseup(function(e) {
                        text = getSelectedText();
                        if ((text = new String(text).replace(/^\s+|\s+$/g,'')) && text != '') {
                            $('#name').val(text);
                                if(!infoBubble) {
                                        infoBubble = $('<a>').attr({
                                        id: 'infoBubble'
                                    }).hide();
                                    infoBubble.html('Classify');
                                    infoBubble.mousedown(function(e){
                                       $('#documentForm').submit();
                                    });
                                    $('#document').append(infoBubble);
                                }
                            infoBubble.attr('href', 'classify').css({
                                top: e.pageY - 30,
                                left: e.pageX - 13
                            }).fadeIn();
                        }
                    });
                    $(document).mousedown(function (e) {
                        if (infoBubble) infoBubble.fadeOut();
                    });
                });
            </script>
            <form action="<c:url value="/classify" />" id="documentForm" method="post">
                <input type="hidden" id="name" name="name" value="" />
                <a href="#" id="loadDocument" class="button">Load Document</a>
                <div contenteditable="true" id="document" name="document">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec <a contenteditable="false" href="http://www.google.com" class="concept">comnimus</a> massa orci, aliquet at accumsan sit amet, facilisis at odio. Quisque cursus sodales nibh in euismod. Nam et commodo arcu. Aliquam vel ipsum nulla. Morbi id diam id sem congue tristique quis sit amet ligula. Fusce dignissim libero nec nunc laoreet a rhoncus libero semper. Donec semper mattis tellus, lobortis aliquet quam iaculis vitae. Aliquam bibendum venenatis massa elementum malesuada. Praesent quis sapien ultricies nulla interdum auctor. Proin sed neque dui. Nunc ultricies venenatis nisl. Maecenas et tellus leo. Nam euismod tempor quam non dapibus. Proin dignissim, arcu tempor eleifend mattis, mi felis iaculis metus, ut pulvinar odio tellus et erat. Maecenas consectetur massa et urna posuere sed aliquet elit dignissim.

                    Curabitur <a contenteditable="false" href="http://www.youtube.com" class="concept">rendene</a> consequat, leo id iaculis blandit, nisl orci tincidunt ante, quis placerat risus ante sed tortor. Vestibulum vulputate consectetur lectus vel aliquet. Integer egestas posuere mi sit amet imperdiet. Fusce tellus leo, pharetra sit amet tristique a, accumsan id erat. In cursus nunc non diam porta sagittis. Sed et nulla ac diam vestibulum eleifend. Suspendisse tristique quam ut justo placerat egestas eu mollis ipsum. Sed adipiscing diam at elit feugiat sed malesuada est venenatis.

                    Aliquam ornare augue a elit bibendum sagittis. Vestibulum sit amet massa lacus, et <a contenteditable="false" href="http://www.9gag.com" class="concept">repellanda</a> luctus erat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Curabitur ullamcorper, magna nec posuere lobortis, turpis felis blandit lacus, sed varius mauris ante a lorem. Nunc euismod, libero sit amet interdum dapibus, eros nulla malesuada leo, quis porttitor erat elit et massa. In nec est sem, sed hendrerit mi. Mauris mattis, sem non venenatis pharetra, mi est lacinia nisl, venenatis tincidunt lorem ligula sed magna. Nulla facilisi. Vestibulum vitae dolor leo. Pellentesque pretium, risus sit amet pulvinar convallis, lorem quam vehicula mi, sed laoreet urna quam ac sem. Nullam quis enim purus. Nulla eget tellus erat. In orci mauris, varius non dictum vestibulum, semper eget orci. Ut in lectus nisi.

                    Pellentesque fermentum facilisis velit quis fringilla. Donec mollis, sem nec lobortis aliquam, lectus ligula molestie ipsum, commodo sollicitudin odio nulla quis elit. Nunc a eros neque, id lobortis nunc. Proin vel lorem turpis, adipiscing rutrum nunc. Donec eu leo augue, ac aliquam ante. Nullam convallis vulputate orci, eu blandit justo malesuada eu. Aliquam ligula libero, imperdiet eu ultrices semper, tincidunt non dolor. Suspendisse erat massa, iaculis a imperdiet ac, mollis vel lorem. Nunc sit amet faucibus erat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque sodales pretium euismod.

                    Donec hendrerit fermentum metus, vitae tempor velit laoreet non. Nulla feugiat, lacus et fringilla elementum, augue massa fermentum massa, et scelerisque lacus dolor ut lacus. Maecenas imperdiet porta tellus, ut faucibus magna venenatis id. Nulla facilisi. Praesent accumsan tincidunt tristique. Etiam quam lorem, hendrerit in rhoncus eu, adipiscing a diam. Ut feugiat quam vel purus consectetur mollis. Mauris vitae ornare erat. Morbi malesuada volutpat suscipit. Duis quam nibh, sodales vel bibendum sit amet, congue id eros. Quisque magna magna, blandit sed egestas vitae, rutrum nec metus.
                </div>
                <input id="save" type="submit" name="save" value="Save" class="button" />
            </form>
