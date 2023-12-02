/**
 * Represents a generic directed graph using an adjacency list.
 *
 * @param <T> The type of data stored in the vertices of the graph.
 */
public class Graph<T> {
    /**
     * Represents the first adjacency list in the graph.
     */
    AdjList<T> firstList;

    /**
     * The count of vertices in the graph.
     */
    int vertexCount;

    /**
     * The count of edges in the graph.
     */
    int edgeCount;

    /**
     * Represents the last adjacency list in the graph.
     */
    AdjList<T> lastList;

    /**
     * Represents a node in the adjacency list of a directed graph.
     *
     * @param <T> The type of data stored in the vertex.
     */
    private class AdjList<T> {
        /**
         * Reference to the next adjacency list in the graph.
         */
        AdjList<T> nextList;

        /**
         * The data stored in the vertex.
         */
        T data;

        /**
         * The number of outgoing edges (outdegree) from this vertex.
         */
        int outdegree;

        /**
         * Reference to the first member (edge) in the adjacency list.
         */
        Member<T> firstMember;

        /**
         * Constructs an empty adjacency list node.
         */
        AdjList() {
        }

        /**
         * Constructs an adjacency list node with the specified data.
         *
         * @param newVertex The data for the vertex.
         */
        AdjList(T newVertex) {
            data = newVertex;
        }
    }

    /**
     * Represents a member of an adjacency list in a graph.
     *
     * @param <T> The type of the adjacency vertex.
     */
    private class Member<T> {
        /**
         * The adjacent vertex connected by this member.
         */
        T adjVertex;

        /**
         * The weight associated with the connection to the adjacent vertex.
         */
        int weight;

        /**
         * The next member in the adjacency list.
         */
        Member<T> nextMember;

        /**
         * Default constructor for the member.
         */
        Member() {
        }

        /**
         * Parameterized constructor for the member.
         *
         * @param toV    The adjacent vertex.
         * @param weight The weight of the connection to the adjacent vertex.
         */
        Member(T toV, int weight) {
            adjVertex = toV;
            this.weight = weight;
        }
    }

    /**
     * Inserts a new vertex into the graph.
     *
     * @param newVertex The new vertex to be inserted.
     */
    void insertVertex(T newVertex) { // Exercise 1

        // A temporary adjacency list representing the new vertex.
        AdjList<T> temp = new AdjList<T>(); // crate vertex ( adjlist)

        // Set the data of the temporary adjacency list to the new vertex.
        temp.data = newVertex; // Insert data

        if (vertexCount == 0) { // If there is no vertex vertex == first == last
            firstList = temp;
            lastList = temp;

        } else {
            AdjList<T> pointer = firstList;

            for (int i = 0; i < vertexCount - 1; i++) { // loop the vertex untill found empty space ; can you both null
                                                        // or vertexcount -1

                pointer = pointer.nextList;
            }

            pointer.nextList = temp;

        }
        vertexCount++;

    }

    /**
     * Searches for an adjacency list in the graph containing the specified data.
     *
     * @param data The data to be searched for in the adjacency lists.
     * @return The adjacency list containing the specified data, or null if not
     *         found.
     */
    AdjList<T> searchAdjList(T data) { // Exercise 2

        // The current adjacency list being examined, starting from the first adjacency
        // list.
        AdjList<T> current = firstList;

        while (current != null) { // Loop until data == current.data
            if (current.data == data) {

                return current;

            } else
                ;
            {
                current = current.nextList;
            }
        }
        return null; // if not found
    }

    /**
     * Inserts a directed edge with a specified weight from one vertex to another in
     * the graph.
     *
     * @param fromData The data of the vertex from which the edge starts.
     * @param toData   The data of the vertex to which the edge is directed.
     * @param weight   The weight of the edge.
     */
    void insertEdge(T fromData, T toData, int weight) { // Exercise 3 (Assume that fromData and toData exist in the
                                                        // graph)
        // The adjacency list corresponding to the vertex from which the edge starts.
        // AdjList<T> fromAdjList = searchAdjList(fromData);

        // // Creates a new member (edge) with the specified destination vertex and
        // weight
        // Member<T> newMember = new Member<T>(toData, weight);
        // if (fromAdjList == null) {

        // return;
        // }
        // if (fromAdjList.firstMember == null) {
        // fromAdjList.firstMember = newMember;

        // } else {
        // Member<T> temp = fromAdjList.firstMember;

        // while (temp.nextMember != null) {
        // temp = temp.nextMember;}
        // temp.nextMember = newMember;} // edgeCount++;

        AdjList<T> fromAdjList = searchAdjList(fromData); // Origin vertex
        Member<T> newMember = new Member<T>(toData, weight);

        if (fromAdjList == null) { // If no vertex just null
            return;
        } else { // if found vertex
            newMember.nextMember = fromAdjList.firstMember; // if have vertex move the vertex's member to left and
                                                            // replace on the adj.first = member
            fromAdjList.firstMember = newMember;
            fromAdjList.outdegree++; // don't forget to add the outdegree and edge count ++
            edgeCount += 1;

        }

    }

    /**
     * Deletes the directed edge from one vertex to another in the graph.
     *
     * @param fromData The data of the vertex from which the edge starts.
     * @param toData   The data of the vertex to which the edge is directed.
     */
    void deleteEdge(T fromData, T toData) { // Exercise 4

        // The adjacency list corresponding to the vertex from which the edge starts.
        AdjList<T> fromAdjList = searchAdjList(fromData);

        if (fromAdjList == null) // If not found any vertex just skip
            return;

        if (fromAdjList.firstMember != null && toData.equals(fromAdjList.firstMember.adjVertex)) { // If edge is in the
                                                                                                   // first member
            fromAdjList.firstMember = fromAdjList.firstMember.nextMember; // just skil the first vertex
            fromAdjList.outdegree--; // don't forget to outdegree and edgecount --
            edgeCount--;

        } else if (fromAdjList.firstMember != null && !toData.equals(fromAdjList.firstMember.adjVertex)) { // If edge is
                                                                                                           // not the
                                                                                                           // first
            Member<T> temp = fromAdjList.firstMember; // pointer
            while (temp != null && temp.nextMember != null) { // While pointer != and its member is not null
                if (!toData.equals(temp.nextMember.adjVertex)) { // if pointer not equal to deleteedge
                    temp = temp.nextMember;
                } else {

                    temp.nextMember = temp.nextMember.nextMember;
                    fromAdjList.outdegree--;
                    edgeCount--;

                }
            }
        }
    }

    /**
     * Deletes a vertex from the graph, including all edges connected to it.
     *
     * @param vertex The data of the vertex to be deleted.
     */
    void deleteVertex(T vertex) { // Exercise 5

        // The current adjacency list being processed.
        AdjList<T> currentAdjList = firstList;

        // The previous adjacency list before the current one.
        AdjList<T> prevAdjList = firstList;

        while (currentAdjList != null) {

            if (!currentAdjList.data.equals(vertex)) { // if the pointer is not at the deleted vertex
                                                       // Check if it's containing the edge that contain delete vertex
                deleteEdge(currentAdjList.data, vertex);

            } else {

                if (currentAdjList == firstList) { // if vertex is firstlist

                    firstList = currentAdjList.nextList; // skip to nextlist

                } else if (currentAdjList == lastList) {

                    lastList = prevAdjList; // If last null it and reassign lastlist
                    lastList.nextList = null;

                } else {

                    prevAdjList.nextList = currentAdjList.nextList; // If it is between vertex prev.next = curr.next ((
                                                                    // skilling curr ))
                }
                vertexCount--; // If it is in else loop its mean that it's found the vertex so it much be
                               // delete
                edgeCount -= currentAdjList.outdegree; // Delete the edge count by curr.degree since delete the vertex
                                                       // also delete it is edge

            }
            prevAdjList = currentAdjList; // temp = curr ; curr = curr.next
            currentAdjList = currentAdjList.nextList;
        }

    }

    /**
     * Prints the graph by displaying each vertex along with its connected vertices
     * <br>
     * and edge weights. The format of the output is as follows: <br>
     * Vertex1 --> ConnectedVertex1,Weight1 --> ConnectedVertex2,Weight2 --> ...
     * <br>
     * Vertex2 --> ... <br>
     * ... <br>
     * The graph's vertex and edge counts are also displayed at the end.
     */
    void print() {
        AdjList<T> currentList = firstList;
        while (currentList != null) {
            System.out.print(currentList.data);
            Member<T> cMem = currentList.firstMember;
            while (cMem != null) {
                System.out.print("-->" + cMem.adjVertex);
                System.out.print("," + cMem.weight);
                cMem = cMem.nextMember;
            }
            System.out.println("");
            currentList = currentList.nextList;

        }
        System.out.println("The graph consists of " + vertexCount + " vertices " + "and " + edgeCount + " edges.");
    }
}