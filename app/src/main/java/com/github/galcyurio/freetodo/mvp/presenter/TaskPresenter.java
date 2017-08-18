package com.github.galcyurio.freetodo.mvp.presenter;

import com.github.galcyurio.freetodo.commons.BusProvider;
import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.commons.FilterType;
import com.github.galcyurio.freetodo.data.model.Task;
import com.github.galcyurio.freetodo.data.source.LocalTaskRepository;
import com.github.galcyurio.freetodo.mvp.contract.TaskContract;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * @author galcyurio
 */
public final class TaskPresenter implements TaskContract.Presenter {

    private final TaskContract.View mView;
    private final LocalTaskRepository mTaskRepository;

    @Inject
    TaskPresenter(TaskContract.View view, LocalTaskRepository taskRepository) {
        mView = view;
        mTaskRepository = taskRepository;

        mView.appendTasks(fetchTasks(FilterType.ALL));
    }

    @Override
    public void registerBus() {
        BusProvider.get().register(this);
    }

    @Override
    public void unregisterBus() {
        BusProvider.get().unregister(this);
    }

    @Subscribe
    @Override
    public void onAddTaskBtnClicked(Events.AddTaskBtnClickEvent event) {
        mView.showAddTaskUi();
    }

    @Subscribe
    @Override
    public void onFilterBtnClicked(Events.FilterBtnClickEvent event) {
        mView.showFilterPopupUi();
    }

    @Subscribe
    @Override
    public void onFilterPopupClicked(Events.FilterPopupClickEvent event) {
        FilterType type = event.getType();
        List<Task> tasks = fetchTasks(type);
        mView.appendTasks(tasks);
    }

    @Override
    public List<Task> fetchTasks(FilterType type) {
        List<Task> tasks = Lists.newArrayList();

        // TODO: get tasks from repository
        switch (type) {
            case ALL:
                break;
            case ACTIVE:
                break;
            case COMPLETED:
                break;
        }
        return tasks;
    }
}