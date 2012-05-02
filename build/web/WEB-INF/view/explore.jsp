            <form action="" id="exploreForm" method="post">
                <div class="exploreField">
                    <label id="classificationLabel" for="exploreClassification">Classification:</label>
                    <select id="classification">
                        <option>option 1</option>
                        <option>option 2</option>
                        <option>option 3</option>
                        <option>option 4</option>
                        <option>option 5</option>
                    </select>
                </div>
                <div class="exploreField">
                    <label id="categoryLabel" for="exploreCategory">Category:</label>
                    <select id="category">
                        <option>option 1</option>
                        <option>option 2</option>
                        <option>option 3</option>
                        <option>option 4</option>
                        <option>option 5</option>
                    </select>
                </div>
                <div class="exploreField">
                    <input id="search" type="text" />
                    <input id="doSearch" type="submit" class="button" value="Search" />
                </div>
                <table id="conceptsTable">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Category</th>
                            <th>Classification</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Gendandit</td>
                            <td>adverb</td>
                            <td>system</td>
                        </tr>
                        <tr>
                            <td>fugitassimi</td>
                            <td>noun</td>
                            <td>general</td>
                        </tr>
                    </tbody>
                </table>
                <input id="edit" type="submit" name="edit" value="Edit" class="button" />
                <input id="delete" type="submit" name="delete" value="Delete" class="button" />
            </form>
