package com.github.galcyurio.freetodo.mvp;

/**
 * @author galcyurio
 */
public interface BaseView {
    void bindEvents();
    void bindEvents(Object view);
}