# Assumptions

Here are the assumptions made during the implementation of the minimal triangle path:

1. **Input Format**: 
   - The input is well-formed. Each line contains space-separated integers. 
   - Malformed lines, non-numeric strings, or empty lines are filtered out or result in an early termination depending on parsing. Currently, unparseable lines are ignored defensively, but the program will return an error if the overall parsed triangle is empty.

2. **Triangle Structure**:
   - Every row $i$ (0-indexed) has exactly $i + 1$ elements. 
   - If the input does not perfectly form a triangle (e.g. fewer elements on a line than expected), the application handles it by pairing `zipWithIndex` with `index + 1`, assuming `Right` elements exist. If the input is fundamentally malformed where row $N-1$ doesn't match elements reachable from row $N$, an IndexOutOfBounds Exception may occur. We assume the standard perfect triangle structure as provided in the core examples.

3. **Performance Requirement (500 rows)**:
   - A traditional depth-first search would have a time complexity of $O(2^N)$, which would never complete for $N=500$.
   - **Assumption/Decision**: A functional Dynamic Programming (DP) bottom-up approach was used. The `foldRight` approach guarantees $O(N^2)$ time complexity and minimal memory footprint, allowing it to easily calculate paths for a 500-level triangle.
   - We fold bottom-up, starting from the leaf nodes, passing up instances of `PathResult` that contain both the minimal sum up to that point and the history of the path.

4. **Single Minimal Path**:
   - The exercise guidelines state: *"You can assume that there will be only one minimal path"*. 
   - Therefore, if there happens to be a tie when calculating `left.sum <= right.sum`, we bias towards the left path. Since only one true minimal path exists recursively, ties will not impact the final correctness of the global minimal path.

5. **Runtime execution via SBT**:
   - SBT is notoriously difficult with intercepting and properly piping `<` or `cat |` standard input when running `sbt run`. 
   - Therefore, the project utilizes the `sbt-assembly` plugin to create a Fat JAR. The official runtime execution assumes running via `java -jar` so standard console InputStreams perform optimally.
