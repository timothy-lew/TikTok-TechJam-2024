"use client";

import { useAuth } from "@/hooks/auth-provider";
import { useEffect, useState } from "react";

import { useFetchListing } from "@/hooks/useFetchListing";
import { Listing } from "./table";

type EditListingProps = {
  itemId: string;
  setEditListing: (itemId: string | null) => void;
};

const EditListing = ({ itemId, setEditListing }: EditListingProps) => {
  const auth = useAuth();
  const [accessToken, setAccessToken] = useState<string>("");
  const [file, setFile] = useState<File | null>(null);

  useEffect(() => {
    const fetchAccessToken = () => {
      auth?.obtainAccessToken().then((res) => {
        setAccessToken(res || "none");
      });
    };
    fetchAccessToken();
  }, []);

  const fileChangeHandler = (e: any) => {
    setFile(e?.target?.files[0]);
    console.log(e?.target?.files[0]);
  };

  const listing = useFetchListing({ itemId, accessToken }) as Listing;

  type ListingFormProps = {
    listing: Listing;
    onSubmit: (listing: Listing) => void;
  };
  const ListingForm = ({ listing, onSubmit }: ListingFormProps) => {
    return (
      <div>
        <form
          onSubmit={(e: any) => {
            e.preventDefault();
            console.log(e);
            const listingValue = {
              ...listing,
              name: (e.target as any).name.value,
              description: (e.target as any).description.value,
              price: (e.target as any).price.value,
              tokTokenPrice: (e.target as any).toktoken.value,
              quantity: (e.target as any).quantity.value,
              imageUrl: e.target[5].files[0],
            };
            console.log(e);
            onSubmit(listingValue);
          }}
        >
          {/* <form method="PUT" action={`${}/api/items/${itemId}`} encType="multipart/form-data" >
                <form method="PUT" action={`${}/api/items/667c2015fe08ec33cf32e736`} encType="multipart/form-data" target="_parent"> */}
          <div className="flex flex-col mb-4">{itemId}</div>
          <div className="flex flex-col mb-4">
            <label className="text-left">Name</label>
            <input
              name="name"
              type="text"
              className="border border-gray-300 p-2 rounded-lg"
              defaultValue={listing?.name}
            />
          </div>
          <div className="flex flex-col mb-4">
            <label className="text-left">Description</label>
            <input
              name="description"
              type="text"
              className="border border-gray-300 p-2 rounded-lg"
              defaultValue={listing?.description}
            />
          </div>
          <div className="flex flex-col mb-4">
            <label className="text-left">Price</label>
            <input
              name="price"
              type="text"
              className="border border-gray-300 p-2 rounded-lg"
              defaultValue={listing?.price}
            />
          </div>
          <div className="flex flex-col mb-4">
            <label className="text-left">Tok Token Price</label>
            <input
              name="toktoken"
              type="text"
              className="border border-gray-300 p-2 rounded-lg"
              defaultValue={listing?.tokTokenPrice}
            />
          </div>
          <div className="flex flex-col mb-4">
            <label className="text-left">Quantity</label>
            <input
              name="quantity"
              type="text"
              className="border border-gray-300 p-2 rounded-lg"
              defaultValue={listing?.quantity}
            />
          </div>
          <div className="flex flex-col mb-4">
            <label className="text-left">Image</label>
            <div className="flex mb-4">
              <img
                className="mr-4"
                alt="No Image"
                style={{ height: 50, width: 50, objectFit: "cover" }}
                src={`data:image/png;base64, ${listing?.imageUrl}`}
              />
              <input
                name="image"
                type="file"
                accept="image/*"
                className="border border-gray-300 p-2 rounded-lg"
              />
            </div>
          </div>
          Broken
          <div className="flex justify-center">
            <button className="bg-blue-500 text-white px-4 py-2 rounded-lg">
              Save
            </button>
          </div>
        </form>
      </div>
    );
  };

  // const onSubmit = (listing: Listing) => {
  //     fetch(`${}/api/items/${itemId}`, {
  //         method: 'PUT',
  //         headers: {
  //             'Content-Type': 'multipart/form-data; boundary=B0EC8D07-EBF1-4EA7-966C-E492A9F2C36E',
  //             'Authorization': `Bearer ${accessToken}`
  //         },
  //         body: {
  //             name: listing.name,
  //             description: listing.description,
  //             price: listing.price,
  //             tokTokenPrice: listing.tokTokenPrice,
  //             quantity: listing.quantity,
  //             image: listing.imageUrl
  //         }
  //     })
  // }

  const onSubmit = (listing: Listing) => {
    console.log("onSubmit", listing);
    var data = new FormData();
    data.append("name", listing.name);
    data.append("description", listing.description);
    data.append("price", listing.price.toString());
    data.append("quantity", listing.quantity.toString());
    if (listing.imageUrl) {
      data.append("image", listing.imageUrl);
    }
    fetch(`${}/api/items/${itemId}`, {
      method: "put",
      headers: {
        "Content-Type":
          "multipart/form-data; boundary=B0EC8D07-EBF1-4EA7-966C-E492A9F2C36E",
        Authorization: `Bearer ${accessToken}`,
      },
      body: data,
    });
  };

  // const onSubmit = (listing: Listing) => {
  //     const formData = new FormData();
  //     formData.append("name", listing.name);
  //     formData.append("description", listing.description);
  //     formData.append("price", listing.price.toString());
  //     formData.append("tokTokenPrice", listing.tokTokenPrice.toString());
  //     formData.append("quantity", listing.quantity.toString());
  //     formData.append("image", listing.imageUrl);
  //     axios.put(`${}/api/items/${itemId}`, formData, {
  //         headers: {
  //             'Content-Type': 'multipart/form-data',
  //             'Authorization': `Bearer ${accessToken}`
  //         }
  //     })
  // }

  return (
    <div
      id="edit-listing-popup"
      className="bg-black/50 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 h-full flex items-center justify-center"
      onClick={(e) => {
        setEditListing(null);
      }}
    >
      <div
        className="relative p-4 w-full max-w-md h-full md:h-auto z-1"
        onClick={(e) => {
          e.stopPropagation();
        }}
      >
        <div className="relative bg-white rounded-lg shadow">
          <div className="text-center p-5">
            <p className="mb-3 text-xl font-semibold leading-5 text-slate-900">
              {listing?.name ? `${listing.name}` : "Loading"}
            </p>
            <ListingForm listing={listing} onSubmit={onSubmit} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditListing;
