0x004040a6:	pusha
0x004040a7:	call 0x004040ac
0x004040ac:	popl %eax
0x004040ad:	addl %eax, $0x29f<UINT32>
0x004040b2:	movl %esi, (%eax)
0x004040b4:	addl %esi, %eax
0x004040b6:	subl %eax, %eax
0x004040b8:	movl %edi, %esi
0x004040ba:	lodsw %ax, %ds:(%esi)
0x004040bc:	shll %eax, $0xc<UINT8>
0x004040bf:	movl %ecx, %eax
0x004040c1:	pushl %eax
0x004040c2:	lodsl %eax, %ds:(%esi)
0x004040c3:	subl %ecx, %eax
0x004040c5:	addl %esi, %ecx
0x004040c7:	movl %ecx, %eax
0x004040c9:	pushl %edi
0x004040ca:	pushl %ecx
0x004040cb:	decl %ecx
0x004040cc:	movb %al, 0x6(%ecx,%edi)
0x004040d0:	movb (%ecx,%esi), %al
0x004040d3:	jne 0x004040cb
0x004040d5:	movl %edx, %esi
0x004040d7:	movl %ecx, %edi
0x004040d9:	call 0x0040413a
0x0040413a:	pushl %ebp
0x0040413b:	movl %ebp, %esp
0x0040413d:	subl %esp, $0x14<UINT8>
0x00404140:	movb %al, (%edx)
0x00404142:	pushl %esi
0x00404143:	xorl %esi, %esi
0x00404145:	incl %esi
0x00404146:	cmpl 0x8(%ebp), %esi
0x00404149:	movl -16(%ebp), %ecx
0x0040414c:	movb (%ecx), %al
0x0040414e:	movl -8(%ebp), %esi
0x00404151:	movb -1(%ebp), $0x0<UINT8>
0x00404155:	jbe 483
0x0040415b:	pushl %ebx
0x0040415c:	pushl %edi
0x0040415d:	cmpb -1(%ebp), $0x0<UINT8>
0x00404161:	movb %cl, (%edx,%esi)
0x00404164:	je 0x00404172
0x00404172:	incl %esi
0x00404173:	andl -12(%ebp), $0x0<UINT8>
0x00404177:	movb -2(%ebp), %cl
0x0040417a:	movzbl %eax, -1(%ebp)
0x0040417e:	movl %edi, 0x8(%ebp)
0x00404181:	subl %edi, %eax
0x00404183:	cmpl %esi, %edi
0x00404185:	jae 416
0x0040418b:	testb %cl, %cl
0x0040418d:	jns 0x004042aa
0x004042aa:	cmpb -1(%ebp), $0x0<UINT8>
0x004042ae:	movzbl %ebx, (%edx,%esi)
0x004042b2:	je 0x004042c1
0x004042c1:	movl %edi, -8(%ebp)
0x004042c4:	movl %eax, -16(%ebp)
0x004042c7:	incl -8(%ebp)
0x004042ca:	movb (%eax,%edi), %bl
0x004042cd:	incl %esi
0x004042ce:	incl -12(%ebp)
0x004042d1:	shlb %cl
0x004042d3:	cmpl -12(%ebp), $0x8<UINT8>
0x004042d7:	movb -2(%ebp), %cl
0x004042da:	jl 0x0040417a
0x004042e0:	jmp 0x0040432b
0x0040432b:	movzbl %eax, -1(%ebp)
0x0040432f:	movl %ecx, 0x8(%ebp)
0x00404332:	subl %ecx, %eax
0x00404334:	cmpl %esi, %ecx
0x00404336:	jb 0x0040415d
0x00404193:	cmpb -1(%ebp), $0x0<UINT8>
0x00404197:	movl %ebx, (%edx,%esi)
0x0040419a:	je 0x0040419f
0x0040419f:	andl %ebx, $0xfffff<UINT32>
0x004041a5:	incl %esi
0x004041a6:	cmpl -8(%ebp), $0x881<UINT32>
0x004041ad:	movl %edi, %ebx
0x004041af:	jae 32
0x004041b1:	shrl %edi
0x004041b3:	testb %bl, $0x1<UINT8>
0x004041b6:	je 0x004041cc
0x004041cc:	andl %edi, $0x7f<UINT8>
0x004041cf:	jmp 0x00404216
0x00404216:	incl %edi
0x00404217:	cmpb -1(%ebp), $0x0<UINT8>
0x0040421b:	je 0x00404226
0x00404226:	xorl %ebx, %ebx
0x00404228:	movw %bx, (%edx,%esi)
0x0040422c:	andl %ebx, $0xfff<UINT32>
0x00404232:	movzbl %eax, -1(%ebp)
0x00404236:	xorb -1(%ebp), $0x1<UINT8>
0x0040423a:	addl %esi, %eax
0x0040423c:	movl %eax, %ebx
0x0040423e:	andl %eax, $0xf<UINT8>
0x00404241:	cmpl %eax, $0xf<UINT8>
0x00404244:	je 0x0040424b
0x00404246:	leal %ebx, 0x3(%eax)
0x00404249:	jmp 0x00404283
0x00404283:	movl %eax, -8(%ebp)
0x00404286:	subl %eax, %edi
0x00404288:	testl %ebx, %ebx
0x0040428a:	je 66
0x0040428c:	movl %edi, -16(%ebp)
0x0040428f:	addl %eax, %edi
0x00404291:	movl -20(%ebp), %ebx
0x00404294:	movl %ebx, -8(%ebp)
0x00404297:	movb %cl, (%eax)
0x00404299:	incl -8(%ebp)
0x0040429c:	incl %eax
0x0040429d:	decl -20(%ebp)
0x004042a0:	movb (%edi,%ebx), %cl
0x004042a3:	jne 0x00404294
0x004042a5:	movb %cl, -2(%ebp)
0x004042a8:	jmp 0x004042ce
0x004042b4:	movzbl %eax, 0x1(%edx,%esi)
0x004042b9:	shrl %ebx, $0x4<UINT8>
0x004042bc:	shll %eax, $0x4<UINT8>
0x004042bf:	orl %ebx, %eax
0x00404166:	movb %al, 0x1(%edx,%esi)
0x0040416a:	shrb %cl, $0x4<UINT8>
0x0040416d:	shlb %al, $0x4<UINT8>
0x00404170:	orb %cl, %al
0x0040419c:	shrl %ebx, $0x4<UINT8>
0x0040421d:	movzwl %ebx, (%edx,%esi)
0x00404221:	shrl %ebx, $0x4<UINT8>
0x00404224:	jmp 0x00404232
0x004041b8:	andl %edi, $0x7ff<UINT32>
0x004041be:	addl %esi, %eax
0x004041c0:	addl %edi, $0x81<UINT32>
0x004041c6:	xorb -1(%ebp), $0x1<UINT8>
0x004041ca:	jmp 0x00404217
0x0040424b:	incl %esi
0x0040424c:	cmpl %ebx, $0xfff<UINT32>
0x00404252:	je 8
0x00404254:	shrl %ebx, $0x4<UINT8>
0x00404257:	addl %ebx, $0x12<UINT8>
0x0040425a:	jmp 0x00404283
0x00404294:	addb -8(%ebp), %bl
0x00404294:	popl %ebp
0x00404295:	popl %ebp
0x00404296:	clc
0x00404294:	clc
0x00404294:	movb %bl, -8(%ebp)
0x00404294:	minps %xmm7, %xmm0
0x00404294:	movb %dh, $0x5d<UINT8>
0x00404294:	incl %ebp
0x00404294:	null
