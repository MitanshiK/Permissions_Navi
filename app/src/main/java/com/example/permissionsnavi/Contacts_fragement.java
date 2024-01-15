//package com.example.permissionsnavi;
//
//import android.Manifest;
//import android.content.Context;
//import android.database.Cursor;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.provider.ContactsContract;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Contacts_fragement extends Fragment {
//
//    View view;
//    RecyclerView contRecy;
//    List<ContModal> contactList;
//    ContAdaptor adaptor;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_contacts_fragement, container, false);
//        initial();
//        return view;
//
//    }
//
//    private void initial() {
//        contRecy = view.findViewById(R.id.contRecy);
//        contactList = new ArrayList<>();
//
//        contRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adaptor = new ContAdaptor(getContext(), contactList);
//        contRecy.setAdapter(adaptor);
//
//        Dexter.withActivity(getActivity())
//                .withPermission(Manifest.permission.READ_CONTACTS)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        if (response.getPermissionName().equals(Manifest.permission.READ_CONTACTS)) {
//                            //getContacts();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//                        Toast.makeText(getActivity(), "permission granted", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();
//    }
////    private void getContacts() {
////        Cursor phones=getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
////   while (phones.moveToNext()){
////       String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
////       String phoneNo=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
////
////       ContModal contModal=new ContModal(name,phoneNo);
////       contactList.add(contModal);
////       adaptor.notifyDataSetChanged();
////   }
////   }
//}
//
//class ContAdaptor extends RecyclerView.Adapter<ContAdaptor.BindingClass> {
//    Context context;
//    List<ContModal> contactList;
//
//    public ContAdaptor(Context context, List<ContModal> contactList) {
//        this.context = context;
//        this.contactList = contactList;
//    }
//
//    @NonNull
//    @Override
//    public ContAdaptor.BindingClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.contact_lay, parent, false);
//        return new BindingClass(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContAdaptor.BindingClass holder, int position) {
//        ContModal contModal = contactList.get(position);
//
//        holder.contName.setText(contModal.getName());
//        holder.contNo.setText(contModal.getNo());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return contactList.size();
//    }
//
//    public static class BindingClass extends RecyclerView.ViewHolder {
//        TextView contName, contNo;
//
//        public BindingClass(@NonNull View itemView) {
//            super(itemView);
//            contName = itemView.findViewById(R.id.contName);
//            contNo = itemView.findViewById(R.id.contNo);
//        }
//    }
//}