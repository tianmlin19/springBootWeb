package com.tml.javaCore.dataStructure.tree;

import java.util.Objects;
import lombok.Data;

/**
 * @author tianmlin19
 * @description 普通的树节点
 * @date 2019/7/23 11:48
 * @since 1.0
 */
@Data
public class TreeNode<T> {

    private T value;

    private TreeNode<T> leftChild;

    private TreeNode<T> rightChild;

    TreeNode(T value) {
        this.value = value;
    }

    TreeNode() {

    }

    /**
     * 增加左节点数据
     */
    public void addLeftNode(T leftData) {
        TreeNode<T> leftChild = new TreeNode(leftData);
        this.leftChild = leftChild;
    }


    /**
     * 增加右节点数据
     */
    public void addRightNode(T rightData) {
        TreeNode<T> rightChild = new TreeNode(rightData);
        this.rightChild = rightChild;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TreeNode<?> treeNode = (TreeNode<?>) o;

        return value != null ? value.equals(treeNode.value) : treeNode.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
