package fitnesstracker.view;

import fitnesstracker.controller.WorkoutPlanManagerController;
import fitnesstracker.model.WorkoutPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutPlanManagerView extends JPanel {
    private WorkoutPlanManagerController controller;
    private DefaultListModel<WorkoutPlan> planListModel;
    private JList<WorkoutPlan> planJList;
    private JButton addPlanButton;
    private JButton deletePlanButton;
    private JButton editPlanButton;

    public WorkoutPlanManagerView() {
        controller = new WorkoutPlanManagerController();
        initComponents();
        layoutComponents();
        registerEvents();
    }

    private void initComponents() {
        planListModel = new DefaultListModel<>();
        for (WorkoutPlan plan : controller.getWorkoutPlans()) {
            planListModel.addElement(plan);
        }
        planJList = new JList<>(planListModel);
        planJList.setBorder(BorderFactory.createTitledBorder("Workout Plans"));
        addPlanButton = new JButton("Add Plan");
        deletePlanButton = new JButton("Delete Plan");
        editPlanButton = new JButton("Edit Plan");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(new JScrollPane(planJList), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPlanButton);
        buttonPanel.add(deletePlanButton);
        buttonPanel.add(editPlanButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void registerEvents() {
        addPlanButton.addActionListener(e -> {
            String planName = JOptionPane.showInputDialog(WorkoutPlanManagerView.this, "Enter plan name:");
            if (planName != null && !planName.trim().isEmpty()) {
                controller.addWorkoutPlan(planName.trim());
                refreshPlanList();
            }
        });

        deletePlanButton.addActionListener(e -> {
            WorkoutPlan selected = planJList.getSelectedValue();
            if (selected != null) {
                controller.deleteWorkoutPlan(selected);
                refreshPlanList();
            } else {
                JOptionPane.showMessageDialog(WorkoutPlanManagerView.this, "Select a plan to delete.");
            }
        });

        editPlanButton.addActionListener(e -> {
            WorkoutPlan selected = planJList.getSelectedValue();
            if (selected != null) {
                JFrame editFrame = new JFrame("Edit Plan: " + selected.getPlanName());
                editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editFrame.setSize(600, 400);
                // WorkoutPlanView is assumed to let you edit exercises in a plan.
                WorkoutPlanView planView = new WorkoutPlanView(selected);
                editFrame.add(planView);
                editFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(WorkoutPlanManagerView.this, "Select a plan to edit.");
            }
        });
    }

    private void refreshPlanList() {
        planListModel.clear();
        for (WorkoutPlan plan : controller.getWorkoutPlans()) {
            planListModel.addElement(plan);
        }
    }
}
