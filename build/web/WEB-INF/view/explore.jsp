            <form action="" id="lookUpForm" method="post">
                <label for="searchQuery">Search</label>
                <input id="searchQuery" type="text" />
                <input id="doSearch" type="submit" class="button" value="Search" />
                <select>
                    <option>classification</option>
                </select>
                <select>
                    <option>category</option>
                </select>
                <table>
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
                <input id="exploreClassify" type="submit" name="classify" value="Classify" class="button" />
                <input id="exploreDelete" type="submit" name="delete" value="Delete" class="button" />
            </form>
