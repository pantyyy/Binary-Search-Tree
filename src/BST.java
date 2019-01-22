
//二分搜索树中的每个元素必须具有可比较性
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    //二叉树的根节点
    private Node root;
    //树中的有效元素个数
    private int size;

    public BST(){
        root = null;
        size = 0;
    }


    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}


    //向二叉树中添加元素
    public void add(E e){
        //判断是否是空的二叉树
        if(root == null){
            root = new Node(e);
            size++;
        }else
            add(root , e);
    }

    //向以node为根节点的二叉搜索树添加元素
//    private void add(Node node , E e){
//        //递归结束条件(最基本的模式)
//        //1.相等 , 2.左节点为空 , 3.右节点为空
//        if(e.equals(node.e)){
//            return;
//        //插入左节点
//        }else if(e.compareTo(node.e) < 0 && node.left == null){
//            node.left = new Node(e);
//            size++;
//            return;
//        }
//        //插入右节点
//        else if(e.compareTo(node.e) > 0 && node.right == null){
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//
//        //由变小的规模解决当前问题
//        //更小的规模就是往左子树或者右子树中插入
//        if(e.compareTo(node.e) < 0 )
//            add(node.left , e);
//        else
//            add(node.right , e);
//    }

    //改进上面的添加函数
    //向以node为根节点的二叉搜索树中插入元素 , 并返回这颗树的根节点
    private Node add(Node node , E e){
        //递归结束的条件 : 当传入的node为null时 , 就需要创建节点了
        if(node == null){
            //一个节点可以看做是一颗二叉搜索树 , 所以返回这颗树的根节点
            size++;
            //就是返回node
            return new Node(e);
        }

        //更小的规模
        //判断是插入左子树还是右子树
        if(e.compareTo(node.e) < 0)
            //因为add函数返回的是以node.left为节点的二叉树
            //所以需要接在当前的树上
            node.left = add(node.left , e);
        //因为有等于的情况没有判断 , 所以不能直接使用else
        //会把等于的情况包括进去
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right , e);

        return node;
    }


    //查看二分搜索树中是否包含指定元素
    public boolean contain(E e){
        return contain(root , e);
    }

    //在以node为根节点的树中寻找元素e
    private boolean contain(Node node , E e){
        //递归结束条件
        if(node == null){
            return false;
        }

        if(e.compareTo(node.e) == 0){
            return true;    //找到了目标元素
        }else if(e.compareTo(node.e) < 0){
            //向左子树中寻找
            return contain(node.left , e);
        }else
            return contain(node.right , e);
    }

    //前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        //结束条件
        if(node == null){
            return;
        }

        //前序遍历 , 在第一次访问节点的时候就访问该元素
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null)
            return;

        //中序遍历是访问节点的第2次才访问其中的值
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }

        //后序遍历是第二次才访问元素
        postOrder(node.left);
        postOrder(node.right);

        System.out.println(node.e);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateString(root , res , 0);
        return res.toString();
    }


    //生成以node为根节点的字符串描述
    private void generateString(Node node , StringBuilder res , int depth){
        //使用先序遍历的方法遍历元素
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateString(node.left , res , depth + 1);
        generateString(node.right , res , depth + 1);
    }

    //根据对应的层数 , 生成对应个数的--
    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i++){
            res.append("--");
        }
        return res.toString();
    }



    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        //bst.preOrder();

        System.out.println(bst);

        //bst.inOrder();
        //System.out.println(bst);
    }
























}
