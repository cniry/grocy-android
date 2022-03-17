/*
 * This file is part of Grocy Android.
 *
 * Grocy Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Grocy Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Grocy Android. If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2020-2022 by Patrick Zedler and Dominic Zedler
 */

package xyz.zedler.patrick.grocy.fragment.bottomSheetDialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.List;
import xyz.zedler.patrick.grocy.R;
import xyz.zedler.patrick.grocy.activity.MainActivity;
import xyz.zedler.patrick.grocy.databinding.FragmentBottomsheetPendingProductsBinding;
import xyz.zedler.patrick.grocy.model.PendingProduct;
import xyz.zedler.patrick.grocy.util.ClickUtil;

public class PendingProductsBottomSheet extends BaseBottomSheet {

    private final static String TAG = PendingProductsBottomSheet.class.getSimpleName();

    private MainActivity activity;
    private FragmentBottomsheetPendingProductsBinding binding;
    private LiveData<List<PendingProduct>> pendingProducts;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), R.style.Theme_Grocy_BottomSheetDialog);
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        activity = (MainActivity) requireActivity();
        binding = FragmentBottomsheetPendingProductsBinding
                .inflate(inflater, container, false);
        binding.setBottomSheet(this);
        binding.setClickUtil(new ClickUtil());
        return binding.getRoot();
    }

    public void startWorkflow() {
        activity.getCurrentFragment().addPendingProducts();
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @NonNull
    @Override
    public String toString() {
        return TAG;
    }
}