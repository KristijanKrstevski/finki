using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace EShop.Web.Data.Migrations
{
    /// <inheritdoc />
    public partial class TestThird : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_Products_ShoppingCartId",
                table: "ProductShoppingCarts");

            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ProductId",
                table: "ProductShoppingCarts");

            migrationBuilder.AlterColumn<string>(
                name: "Email",
                table: "AspNetUsers",
                type: "nvarchar(256)",
                maxLength: 256,
                nullable: true,
                oldClrType: typeof(string),
                oldType: "nvarchar(256)",
                oldMaxLength: 256);

            migrationBuilder.AddColumn<string>(
                name: "Address",
                table: "AspNetUsers",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_Products_ProductId",
                table: "ProductShoppingCarts",
                column: "ProductId",
                principalTable: "Products",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ShoppingCartId",
                table: "ProductShoppingCarts",
                column: "ShoppingCartId",
                principalTable: "ShoppingCarts",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_Products_ProductId",
                table: "ProductShoppingCarts");

            migrationBuilder.DropForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ShoppingCartId",
                table: "ProductShoppingCarts");

            migrationBuilder.DropColumn(
                name: "Address",
                table: "AspNetUsers");

            migrationBuilder.AlterColumn<string>(
                name: "Email",
                table: "AspNetUsers",
                type: "nvarchar(256)",
                maxLength: 256,
                nullable: false,
                defaultValue: "",
                oldClrType: typeof(string),
                oldType: "nvarchar(256)",
                oldMaxLength: 256,
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_Products_ShoppingCartId",
                table: "ProductShoppingCarts",
                column: "ShoppingCartId",
                principalTable: "Products",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProductShoppingCarts_ShoppingCarts_ProductId",
                table: "ProductShoppingCarts",
                column: "ProductId",
                principalTable: "ShoppingCarts",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
